import DataSourceFakes
import Models
import Nimble
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

@testable import PresentationProfile

final class ProfilePagerViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: ProfilePagerViewModel!

        Given("I open the profile of the third dog in the grid") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                DogsFeedFactory(container).withLoadedDogs(count: 5)
                viewModel = container.resolve(ProfilePagerViewModel.self, argument: 3)
            }

            justBeforeEach {
                viewModel.onViewAppear()
            }

            Then("The pager shows every loaded dog starting from the tapped one") {
                expect(viewModel.state.pages.map(\.name)) == ["Dog 1", "Dog 2", "Dog 3", "Dog 4", "Dog 5"]
                expect(viewModel.state.initialPage) == 2
            }

            When("I block the fourth dog") {
                justBeforeEach {
                    let fourthDog = viewModel.state.pages[3].domain
                    let moderationViewModel = container.resolve(ProfileModerationViewModel.self, argument: fourthDog)!
                    moderationViewModel.onBlockTap()
                    moderationViewModel.onDialogConfirm()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The blocked dog disappears from the pager") {
                    expect(viewModel.state.pages.map(\.name)) == ["Dog 1", "Dog 2", "Dog 3", "Dog 5"]
                }
            }
        }
    }
}
