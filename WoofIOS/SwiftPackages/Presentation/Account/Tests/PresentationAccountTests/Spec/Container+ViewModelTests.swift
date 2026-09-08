import PresentationAccount
import Swinject
import TestUtils

extension Container {
    func injectForViewModelTests() -> Container {
        self
            .injectCoreForViewModelTests()
            .injectPresentationAccountGenerated()
    }
}
