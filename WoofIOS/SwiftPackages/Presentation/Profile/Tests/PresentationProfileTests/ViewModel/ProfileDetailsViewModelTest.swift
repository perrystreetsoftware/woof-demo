import DataSourceFakes
import Models
import Nimble
import PresentationProfile
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class ProfileDetailsViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: ProfileDetailsViewModel!
        let dog = DogFactory().withId(1).produce()

        Given("I open a dog profile") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                DogsDataSourceFactory(container).withDogs(count: 3)
                viewModel = container.resolve(ProfileDetailsViewModel.self, argument: dog)
            }

            Then("The name shows instantly while the rest of the profile is loading") {
                expect(viewModel.state) == ProfileDetailsUIModel(
                    name: "Dog 1",
                    summary: nil,
                    heroTags: [],
                    content: .loading
                )
            }

            When("The profile loads") {
                justBeforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The full profile is displayed") {
                    expect(viewModel.state) == ProfileDetailsUIModel(
                        name: "Dog 1",
                        summary: ProfileSummaryUIModel(
                            ageInYears: 4,
                            breed: "Golden Retriever",
                            neighborhood: "Kolonaki, Athens"
                        ),
                        heroTags: ["Playful", "Cuddly"],
                        content: .visible(
                            sections: [
                                .about(
                                    name: "Dog 1",
                                    bio: "Loves swimming, tennis balls, and stealing snacks."
                                ),
                                .personality(tags: ["Playful", "Cuddly"]),
                                .details(
                                    rows: [
                                        .breed("Golden Retriever"),
                                        .age(4),
                                        .size(.large),
                                        .neighborhood("Kolonaki, Athens"),
                                        .favoriteActivity("Swimming at the beach"),
                                    ]
                                ),
                            ]
                        )
                    )
                }
            }

            When("The profile fails to load") {
                beforeEach {
                    DogsDataSourceFactory(container).withDogProfileError()
                }

                justBeforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The profile stays in the loading state and an error is emitted") {
                    expect(viewModel.state.content) == .loading
                    expect(viewModel.error).toNot(beNil())
                }
            }
        }
    }
}
