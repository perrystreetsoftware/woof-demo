import SwiftCompilerPlugin
import SwiftSyntax
import SwiftSyntaxBuilder
import SwiftSyntaxMacros

public struct StaticLetCaseIterableMacro: MemberMacro {
    public static func expansion(
        of node: AttributeSyntax,
        providingMembersOf declaration: some DeclGroupSyntax,
        conformingTo _: [TypeSyntax],
        in context: some MacroExpansionContext
    ) throws -> [DeclSyntax] {
        let declarationName =
            declaration
            .as(ExtensionDeclSyntax.self)?
            .extendedType
            .as(IdentifierTypeSyntax.self)?
            .name
            .text

        let staticLetProperties = declaration.memberBlock.members
            .compactMap { $0.decl.as(VariableDeclSyntax.self) }
            .filter { varDecl in
                let isStatic = varDecl.modifiers.contains { $0.name.text == "static" }
                let isLet = varDecl.bindingSpecifier.text == "let"
                let isStaticLet = isStatic && isLet

                let variableTypeName = varDecl
                    .bindings
                    .first?
                    .initializer?
                    .value
                    .as(FunctionCallExprSyntax.self)?
                    .calledExpression
                    .as(DeclReferenceExprSyntax.self)?
                    .baseName
                    .text

                let isSameType = variableTypeName == declarationName

                return isStaticLet && isSameType
            }
            .compactMap { $0.bindings.first?.pattern.as(IdentifierPatternSyntax.self)?.identifier.text }

        let casesList = staticLetProperties.map { ".\($0)" }.joined(separator: ",\n    ")

        let allCasesDecl = "static let allCases: [\(declarationName ?? "Self")] = [\n    \(casesList)\n]"

        return [DeclSyntax(stringLiteral: allCasesDecl)]
    }
}
