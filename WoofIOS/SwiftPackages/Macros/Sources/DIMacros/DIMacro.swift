import SwiftCompilerPlugin
import SwiftDiagnostics
import SwiftSyntax
import SwiftSyntaxBuilder
import SwiftSyntaxMacros

extension MemberBlockItemListSyntax {
    var hasConstructorAlready: Bool {
        self.contains(where: { item in
            item.decl.is(InitializerDeclSyntax.self)
        })
    }
}
extension PatternBindingListSyntax.Element {
    var isInt: Bool {
        self.typeAnnotation?.type.as(IdentifierTypeSyntax.self)?.name.text == "Int"
    }

    var isBool: Bool {
        self.typeAnnotation?.type.as(IdentifierTypeSyntax.self)?.name.text == "Bool"
    }

    var isString: Bool {
        self.typeAnnotation?.type.as(IdentifierTypeSyntax.self)?.name.text == "String"
    }

    var isFloat: Bool {
        self.typeAnnotation?.type.as(IdentifierTypeSyntax.self)?.name.text == "Float"
    }

    var isDouble: Bool {
        self.typeAnnotation?.type.as(IdentifierTypeSyntax.self)?.name.text == "Double"
    }

    var isPrimitiveType: Bool {
        self.isInt || self.isBool || self.isString || self.isFloat || self.isDouble
    }
}

extension VariableDeclSyntax {
    var hasDIArgumentAttribute: Bool {
        attributes.contains { element in
            element.as(AttributeSyntax.self)?
                .attributeName.as(IdentifierTypeSyntax.self)?
                .name.text == "DIArgument"
        }
    }

    var isStoredProperty: Bool {
        guard let binding = bindings.first,
            bindings.count == 1,
            !isLazyProperty,
            !isConstant
        else {
            return false
        }

        if binding.isPrimitiveType {
            return false
        }

        if binding.accessorBlock != nil {
            return false
        }

        return true
    }

    var isLazyProperty: Bool {
        modifiers.contains { $0.name.tokenKind == .keyword(Keyword.lazy) }
    }

    var isConstant: Bool {
        bindingSpecifier.tokenKind == .keyword(Keyword.let) && bindings.first?.initializer != nil
    }
}

extension DeclGroupSyntax {
    func storedProperties() -> [VariableDeclSyntax] {
        return memberBlock.members.compactMap { member in
            guard let variable = member.decl.as(VariableDeclSyntax.self),
                variable.isStoredProperty
            else {
                return nil
            }

            return variable
        }
    }
}

enum DIMacroDiagnostic: String, DiagnosticMessage {
    case multipleDIArguments

    var message: String {
        "@DIArgument can be applied to at most one property per class — Swinject codegen only generates a single-argument registration."
    }

    var severity: DiagnosticSeverity { .error }

    var diagnosticID: MessageID {
        MessageID(domain: "DIMacros", id: rawValue)
    }
}

public struct DIMacro: MemberMacro {

    enum Errors: Swift.Error, CustomStringConvertible {
        case invalidInputType

        var description: String {
            "@DI macro is only applicable to structs or classes"
        }
    }

    public static func expansion(
        of node: SwiftSyntax.AttributeSyntax, providingMembersOf declaration: some SwiftSyntax.DeclGroupSyntax,
        conformingTo _: [SwiftSyntax.TypeSyntax],
        in context: some SwiftSyntaxMacros.MacroExpansionContext
    ) throws -> [SwiftSyntax.DeclSyntax] {

        let diArgumentProperties = declaration.memberBlock.members
            .compactMap { $0.decl.as(VariableDeclSyntax.self) }
            .filter(\.hasDIArgumentAttribute)

        if diArgumentProperties.count > 1 {
            for extra in diArgumentProperties.dropFirst() {
                context.diagnose(
                    Diagnostic(node: Syntax(extra), message: DIMacroDiagnostic.multipleDIArguments)
                )
            }
            return []
        }

        let storedProperties: [VariableDeclSyntax] = try {
            if let classDeclaration = declaration.as(ClassDeclSyntax.self) {
                return classDeclaration.storedProperties()
            } else if let structDeclaration = declaration.as(StructDeclSyntax.self) {
                return structDeclaration.storedProperties()
            } else {
                throw Errors.invalidInputType
            }
        }()

        guard declaration.memberBlock.members.hasConstructorAlready == false else {
            return []
        }

        let initArguments = storedProperties.compactMap { property -> (name: String, type: String)? in
            guard let patternBinding = property.bindings.first else {
                return nil
            }

            if property.attributes.count > 0,
                let attSyntax = property.attributes.first?.as(AttributeSyntax.self),
                let ident = attSyntax.attributeName.as(IdentifierTypeSyntax.self),
                ident.name.text == "Published"
            {
                return nil
            }

            guard let name = patternBinding.pattern.as(IdentifierPatternSyntax.self)?.identifier,
                let type = patternBinding.typeAnnotation?.type.as(IdentifierTypeSyntax.self)?.name
            else {
                return nil
            }

            return (name: name.text, type: type.text)
        }

        let initBody: ExprSyntax = "\(raw: initArguments.map { "self.\($0.name) = \($0.name)" }.joined(separator: "\n"))"

        let initDeclSyntax = try InitializerDeclSyntax(
            SyntaxNodeString(stringLiteral: "init(\(initArguments.map { "\($0.name): \($0.type)" }.joined(separator: ", ")))"),
            bodyBuilder: {
                initBody
            }
        )

        let finalDeclaration = DeclSyntax(initDeclSyntax)

        return [finalDeclaration]
    }
}

public struct FactoryCollectionMacro: MemberMacro {

    public static func expansion(
        of node: SwiftSyntax.AttributeSyntax,
        providingMembersOf declaration: some SwiftSyntax.DeclGroupSyntax,
        conformingTo _: [SwiftSyntax.TypeSyntax],
        in context: some SwiftSyntaxMacros.MacroExpansionContext
    ) throws -> [SwiftSyntax.DeclSyntax] {
        return try DIMacro.expansion(of: node, providingMembersOf: declaration, conformingTo: [], in: context)
    }
}

@main
struct DIPlugin: CompilerPlugin {
    let providingMacros: [Macro.Type] = [
        DIMacro.self,
        DINamedMacro.self,
        DIArgumentMacro.self,
        FactoryCollectionMacro.self,
        StaticLetCaseIterableMacro.self,
        StructCopyableMacro.self,
    ]
}
