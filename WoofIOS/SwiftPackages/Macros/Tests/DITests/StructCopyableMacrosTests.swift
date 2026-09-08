import SwiftSyntax
import SwiftSyntaxBuilder
import SwiftSyntaxMacros
import SwiftSyntaxMacrosTestSupport
import XCTest

#if canImport(DIMacros)
    import DIMacros

    let structCopyableTestMacros: [String: Macro.Type] = [
        "StructCopyable": StructCopyableMacro.self
    ]
#endif

final class StructCopyableMacrosTests: XCTestCase {
    func testCopyableMacro() throws {
        #if canImport(DIMacros)
            assertMacroExpansion(
                """
                @StructCopyable
                public struct Account {
                    let name: String
                    let isOnline: Bool
                    let numItems: Int
                    let age: Date?
                    var domainModel: DomainModel
                    public var isNewUser: Bool?

                    static let empty: Account = Account(name: "", isOnline: false, numItems: 0, age: nil, domainModel: DomainModel(), isNewUser: false)
                }
                """,
                expandedSource: """
                    public struct Account {
                        let name: String
                        let isOnline: Bool
                        let numItems: Int
                        let age: Date?
                        var domainModel: DomainModel
                        public var isNewUser: Bool?

                        static let empty: Account = Account(name: "", isOnline: false, numItems: 0, age: nil, domainModel: DomainModel(), isNewUser: false)

                        public func copy(
                            name: String? = nil, isOnline: Bool? = nil, numItems: Int? = nil, age: Date? = nil, domainModel: DomainModel? = nil, isNewUser: Bool? = nil
                        ) -> Account {
                            return Account(
                                    name: name ?? self.name,
                                    isOnline: isOnline != nil ? (isOnline ?? false) : self.isOnline,
                                    numItems: numItems ?? self.numItems,
                                    age: age ?? self.age,
                                    domainModel: domainModel ?? self.domainModel,
                                    isNewUser: isNewUser != nil ? (isNewUser ?? false) : self.isNewUser
                            )
                        }
                    }
                    """,
                macros: structCopyableTestMacros
            )
        #else
            throw XCTSkip("macros are only supported when running tests for the host platform")
        #endif
    }
}
