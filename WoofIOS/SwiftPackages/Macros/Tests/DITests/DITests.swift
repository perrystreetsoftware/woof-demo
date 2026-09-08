import SwiftSyntax
import SwiftSyntaxBuilder
import SwiftSyntaxMacros
import SwiftSyntaxMacrosTestSupport
import XCTest

#if canImport(DIMacros)
    import DIMacros

    let testMacros: [String: Macro.Type] = [
        "DI": DIMacro.self,
        "DIArgument": DIArgumentMacro.self,
    ]
#endif

final class DITests: XCTestCase {

    func testDI() throws {
        #if canImport(DIMacros)
            assertMacroExpansion(
                """
                @DI
                final public class AccountRepository {
                    private let accountApi: AccountApiImplementing
                    private let someOtherApi: SomeOtherApiImplementing
                    private var thisShouldBeIgnored: Int { 5 }
                    private var anInt: Int = 0
                    private var aBool: Bool = false
                }
                """,
                expandedSource: """
                    final public class AccountRepository {
                        private let accountApi: AccountApiImplementing
                        private let someOtherApi: SomeOtherApiImplementing
                        private var thisShouldBeIgnored: Int { 5 }
                        private var anInt: Int = 0
                        private var aBool: Bool = false

                        init(accountApi: AccountApiImplementing, someOtherApi: SomeOtherApiImplementing) {
                            self.accountApi = accountApi
                        self.someOtherApi = someOtherApi
                        }
                    }
                    """,
                macros: testMacros
            )
        #else
            throw XCTSkip("macros are only supported when running tests for the host platform")
        #endif

    }

    func testSkipInit() throws {
        #if canImport(DIMacros)
            assertMacroExpansion(
                """
                @DI
                final public class AccountRepository {
                    private let accountApi: AccountApiImplementing
                    private let someOtherApi: SomeOtherApiImplementing
                    private var thisShouldBeIgnored: Int { 5 }

                    init() {
                    }
                }
                """,
                expandedSource: """
                    final public class AccountRepository {
                        private let accountApi: AccountApiImplementing
                        private let someOtherApi: SomeOtherApiImplementing
                        private var thisShouldBeIgnored: Int { 5 }

                        init() {
                        }
                    }
                    """,
                macros: testMacros
            )
        #else
            throw XCTSkip("macros are only supported when running tests for the host platform")
        #endif

    }

    func testSkipPublished() throws {
        #if canImport(DIMacros)

            assertMacroExpansion(
                """
                @DI
                public final class ProfileViewMediator {
                    @Published public private(set) var profilePhotoIndicator: ProfilePhotoIndicator = .photo(index: 0)
                }
                """,
                expandedSource: """
                    public final class ProfileViewMediator {
                        @Published public private(set) var profilePhotoIndicator: ProfilePhotoIndicator = .photo(index: 0)

                        init() {
                        }
                    }
                    """,
                macros: testMacros
            )
        #else
            throw XCTSkip("macros are only supported when running tests for the host platform")
        #endif

    }

    func testMultipleDIArgumentsEmitsError() throws {
        #if canImport(DIMacros)
            assertMacroExpansion(
                """
                @DI
                public final class FooViewModel {
                    @DIArgument private let film: Film
                    @DIArgument private let source: Source

                    init(film: Film, source: Source) {
                        self.film = film
                        self.source = source
                    }
                }
                """,
                expandedSource: """
                    public final class FooViewModel {
                        private let film: Film
                        private let source: Source

                        init(film: Film, source: Source) {
                            self.film = film
                            self.source = source
                        }
                    }
                    """,
                diagnostics: [
                    DiagnosticSpec(
                        message:
                            "@DIArgument can be applied to at most one property per class — Swinject codegen only generates a single-argument registration.",
                        line: 4,
                        column: 5
                    )
                ],
                macros: testMacros
            )
        #else
            throw XCTSkip("macros are only supported when running tests for the host platform")
        #endif

    }

    func testSingleDIArgumentIsAllowed() throws {
        #if canImport(DIMacros)
            assertMacroExpansion(
                """
                @DI
                public final class FooViewModel {
                    @DIArgument private let film: Film
                    private let logic: GetFilmDetailsLogic

                    init(film: Film, logic: GetFilmDetailsLogic) {
                        self.film = film
                        self.logic = logic
                    }
                }
                """,
                expandedSource: """
                    public final class FooViewModel {
                        private let film: Film
                        private let logic: GetFilmDetailsLogic

                        init(film: Film, logic: GetFilmDetailsLogic) {
                            self.film = film
                            self.logic = logic
                        }
                    }
                    """,
                macros: testMacros
            )
        #else
            throw XCTSkip("macros are only supported when running tests for the host platform")
        #endif

    }

}
