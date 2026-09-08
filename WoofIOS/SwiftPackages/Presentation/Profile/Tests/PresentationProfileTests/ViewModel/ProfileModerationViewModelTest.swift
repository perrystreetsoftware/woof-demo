import DataSourceFakes
import Models
import Nimble
import PresentationProfile
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class ProfileModerationViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: ProfileModerationViewModel!
        var moderationDataSource: FakeModerationDataSource!
        var navigator: FakeNavigator!
        let dog = DogFactory().produce()

        Given("I open a dog profile") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                moderationDataSource = container~>
                navigator = container~>
                viewModel = container.resolve(ProfileModerationViewModel.self, argument: dog)
            }

            justBeforeEach {
                viewModel.onViewAppear()
            }

            Then("No dialog is shown") {
                expect(viewModel.state) == .initial
            }

            When("I choose to report the dog") {
                justBeforeEach {
                    viewModel.onReportTap()
                }

                Then("The report confirmation dialog is shown") {
                    expect(viewModel.state.dialog) == .report(name: "Bruno")
                }

                And("I dismiss the dialog") {
                    justBeforeEach {
                        viewModel.onDialogDismiss()
                    }

                    Then("Nothing is reported") {
                        expect(viewModel.state) == .initial
                        expect(moderationDataSource.reportedDogIds) == []
                    }
                }

                And("I confirm the dialog") {
                    justBeforeEach {
                        viewModel.onDialogConfirm()
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("The dog is reported and a confirmation is shown") {
                        expect(moderationDataSource.reportedDogIds) == [1]
                        expect(viewModel.state) == ProfileModerationViewModel.State(
                            dialog: nil,
                            toast: .reportSent
                        )
                    }
                }
            }

            When("I choose to block the dog and confirm") {
                justBeforeEach {
                    viewModel.onBlockTap()
                    viewModel.onDialogConfirm()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The dog is blocked and I am taken back to the grid") {
                    expect(moderationDataSource.blockedDogIds) == [1]
                    expect(viewModel.state) == .initial
                    expect(navigator.backCount) == 1
                }
            }

            When("Blocking fails") {
                beforeEach {
                    ModerationDataSourceFactory(container).withBlockDogError()
                }

                justBeforeEach {
                    viewModel.onBlockTap()
                    viewModel.onDialogConfirm()
                    TimeAdvancingFactory(container).tick()
                }

                Then("I stay on the profile and an error is emitted") {
                    expect(navigator.backCount) == 0
                    expect(viewModel.error).toNot(beNil())
                }
            }
        }
    }
}
