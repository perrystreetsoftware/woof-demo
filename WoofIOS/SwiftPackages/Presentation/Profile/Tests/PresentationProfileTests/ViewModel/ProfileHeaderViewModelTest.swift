import DataSourceFakes
import Models
import Nimble
import PresentationProfile
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class ProfileHeaderViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: ProfileHeaderViewModel!
        let dog = DogFactory().produce()

        Given("I open a dog profile") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                viewModel = container.resolve(ProfileHeaderViewModel.self, argument: dog)
            }

            justBeforeEach {
                viewModel.onViewAppear()
            }

            Then("The header shows the name, no favorite, and the closed overflow menu") {
                expect(viewModel.state) == ProfileHeaderViewModel.State(
                    name: "Bruno",
                    isFavorite: false,
                    isOverflowExpanded: false,
                    overflowItems: [.report, .block]
                )
            }

            When("I tap the favorite button") {
                justBeforeEach {
                    viewModel.onFavoriteTap()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The dog becomes a favorite") {
                    expect(viewModel.state.isFavorite) == true
                }

                And("I tap the favorite button again") {
                    justBeforeEach {
                        viewModel.onFavoriteTap()
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("The dog is no longer a favorite") {
                        expect(viewModel.state.isFavorite) == false
                    }
                }
            }

            When("Adding a favorite fails") {
                beforeEach {
                    FavoritesDataSourceFactory(container).withAddFavoriteError()
                }

                justBeforeEach {
                    viewModel.onFavoriteTap()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The favorite is rolled back and an error is emitted") {
                    expect(viewModel.state.isFavorite) == false
                    expect(viewModel.error).toNot(beNil())
                }
            }

            When("I open the overflow menu") {
                justBeforeEach {
                    viewModel.onOverflowExpandedChange(true)
                }

                Then("The overflow menu is expanded") {
                    expect(viewModel.state.isOverflowExpanded) == true
                }
            }
        }
    }
}
