import Nimble
import PresentationHome
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class HomeViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: HomeViewModel!

        Given("I open the app") {
            beforeEach {
                container = Container().injectForViewModelTests()
                viewModel = container~>
            }

            justBeforeEach {
                viewModel.onViewAppear()
            }

            Then("The browse tab is selected and back is not intercepted") {
                expect(viewModel.state.selectedTab) == .browse
                expect(viewModel.state.tabs) == [.browse, .favorites, .account]
                expect(viewModel.state.isBackHandled) == false
            }

            When("I select the favorites tab") {
                justBeforeEach {
                    viewModel.onTabSelect(.favorites)
                }

                Then("The favorites tab is selected and back is intercepted") {
                    expect(viewModel.state.selectedTab) == .favorites
                    expect(viewModel.state.isBackHandled) == true
                }

                And("I press back") {
                    justBeforeEach {
                        viewModel.onBackTap()
                    }

                    Then("The browse tab is selected again") {
                        expect(viewModel.state.selectedTab) == .browse
                    }
                }
            }
        }
    }
}
