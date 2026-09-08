import DataSourceFakes
import Models
import Nimble
import PresentationAccount
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class AccountViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: AccountViewModel!

        Given("I open the account tab") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                viewModel = container~>
            }

            Then("The account is loading") {
                expect(viewModel.state) == .loading
            }

            When("The account loads") {
                justBeforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory(container).tick()
                }

                Then("My dog's profile is displayed") {
                    expect(viewModel.state) == .loaded(
                        account: AccountUIModel(
                            name: "Milo",
                            photoUrl: "woof://dogs/border_collie_01.jpg",
                            summary: AccountSummaryUIModel(ageInYears: 3, breed: "Border Collie", neighborhood: "Schöneberg, Berlin"),
                            bio: "Herds tennis balls for a living.",
                            personality: ["Frisbee pro", "Early riser"],
                            rows: [
                                .breed("Border Collie"),
                                .age(3),
                                .size(.medium),
                                .neighborhood("Schöneberg, Berlin"),
                                .favoriteActivity("Agility courses"),
                            ]
                        )
                    )
                }
            }

            When("The account fails to load") {
                beforeEach {
                    AccountDataSourceFactory(container).withAccountError()
                }

                justBeforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The account stays loading and an error is emitted") {
                    expect(viewModel.state) == .loading
                    expect(viewModel.error).toNot(beNil())
                }
            }
        }
    }
}
