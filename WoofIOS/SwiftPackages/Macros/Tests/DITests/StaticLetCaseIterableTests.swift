import SwiftSyntax
import SwiftSyntaxBuilder
import SwiftSyntaxMacros
import SwiftSyntaxMacrosTestSupport
import XCTest

#if canImport(DIMacros)
    import DIMacros

    let staticTestMacros: [String: Macro.Type] = [
        "StaticLetCaseIterable": StaticLetCaseIterableMacro.self
    ]
#endif

final class MacrosTests: XCTestCase {
    func testMacro() throws {
        #if canImport(DIMacros)
            assertMacroExpansion(
                """
                @StaticLetCaseIterable
                public extension Color {
                    static let red = Color(name: "Red")
                    static let blue = Color(name: "Blue")
                    static let green = Color(name: "Green")
                }
                """,
                expandedSource: """
                    public extension Color {
                        static let red = Color(name: "Red")
                        static let blue = Color(name: "Blue")
                        static let green = Color(name: "Green")

                        static let allCases: [Color] = [
                            .red,
                            .blue,
                            .green
                        ]
                    }
                    """,
                macros: staticTestMacros
            )
        #else
            throw XCTSkip("macros are only supported when running tests for the host platform")
        #endif
    }
}
