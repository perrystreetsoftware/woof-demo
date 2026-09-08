import DataSourceFakes
import Models
import Nimble
import PresentationProfile
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class ProfileWoofViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: ProfileWoofViewModel!
        let dog = DogFactory().produce()

        Given("I open a dog profile") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                viewModel = container.resolve(ProfileWoofViewModel.self, argument: dog)
            }

            justBeforeEach {
                viewModel.onViewAppear()
            }

            Then("The woof button is inactive") {
                expect(viewModel.state) == ProfileWoofViewModel.State(hasWoofed: false, toast: nil)
            }

            When("I tap the woof button") {
                justBeforeEach {
                    viewModel.onWoofTap()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The woof is sent and confirmed") {
                    expect(viewModel.state) == ProfileWoofViewModel.State(
                        hasWoofed: true,
                        toast: .woofSent(name: "Bruno")
                    )
                }

                And("The confirmation is dismissed") {
                    justBeforeEach {
                        viewModel.onToastDismiss()
                    }

                    Then("The woof stays active without a confirmation") {
                        expect(viewModel.state) == ProfileWoofViewModel.State(hasWoofed: true, toast: nil)
                    }
                }

                And("I tap the woof button again") {
                    justBeforeEach {
                        viewModel.onToastDismiss()
                        viewModel.onWoofTap()
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("An already woofed error is emitted") {
                        expect(viewModel.error) == .alreadyWoofed
                        expect(viewModel.state.toast).to(beNil())
                    }
                }
            }

            When("Sending the woof fails") {
                beforeEach {
                    WoofsDataSourceFactory(container).withSendWoofError()
                }

                justBeforeEach {
                    viewModel.onWoofTap()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The woof button stays inactive and an error is emitted") {
                    expect(viewModel.state.hasWoofed) == false
                    expect(viewModel.error).toNot(beNil())
                }
            }
        }
    }
}
