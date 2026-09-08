import PresentationNavigation
import PresentationProfile
import Swinject
import TestUtils
import Utils

extension Container {
    func injectForViewModelTests() -> Container {
        self.pss_registerMock(NavigatorImplementing.self, FakeNavigator.self, FakeNavigator.init)
        return
            self
            .injectCoreForViewModelTests()
            .injectPresentationProfileGenerated()
    }
}
