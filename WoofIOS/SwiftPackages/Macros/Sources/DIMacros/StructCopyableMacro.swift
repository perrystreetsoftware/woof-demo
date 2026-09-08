import Foundation
import SwiftCompilerPlugin
import SwiftSyntax
import SwiftSyntaxBuilder
import SwiftSyntaxMacros

public struct StructCopyableMacro: MemberMacro {
    public static func expansion(
        of node: AttributeSyntax,
        providingMembersOf declaration: some DeclGroupSyntax,
        conformingTo _: [TypeSyntax],
        in context: some MacroExpansionContext
    ) throws -> [DeclSyntax] {

        guard let structDecl = declaration.as(StructDeclSyntax.self) else {
            throw MacroError.notAStruct
        }

        let storedProperties = structDecl.memberBlock.members
            .compactMap { member -> VariableDeclSyntax? in
                let variableDecl = member.decl.as(VariableDeclSyntax.self)
                guard let variableDecl else { return nil }

                let isStatic = variableDecl.modifiers.contains { modifier in
                    modifier.name.tokenKind == .keyword(.static)
                }

                guard isStatic == false else { return nil }

                return variableDecl
            }
            .filter { $0.bindings.first?.accessorBlock == nil } // Only stored properties

        let properties: [(String, String)] =
            storedProperties
            .flatMap { $0.bindings }
            .compactMap { binding in
                guard let identifier = binding.pattern.as(IdentifierPatternSyntax.self),
                    let type = binding.typeAnnotation?.type
                else {
                    return nil
                }
                return (identifier.identifier.text, type.description.trimmingCharacters(in: .whitespaces))
            }

        guard !properties.isEmpty else {
            return []
        }

        let parameters = properties.map { name, type in
            if type.hasSuffix("?") {
                "\(name): \(type) = nil"
            } else {
                "\(name): \(type)? = nil"
            }
        }.joined(separator: ", ")

        let assignments = properties.map { name, type in
            if type.hasPrefix("Bool") {
                "            \(name): \(name) != nil ? (\(name) ?? false) : self.\(name)"
            } else {
                "            \(name): \(name) ?? self.\(name)"
            }
        }.joined(separator: ",\n")

        let copyMethod = """
            public func copy(
                \(parameters)
            ) -> \(structDecl.name.text) {
                return \(structDecl.name.text)(
            \(assignments)
                )
            }
            """

        return [DeclSyntax(stringLiteral: copyMethod)]
    }

    enum MacroError: Error {
        case notAStruct
    }
}
