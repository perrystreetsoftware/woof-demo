import DataSourceFakes
import Models
import Nimble
import PresentationProfile
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class ProfileMessageViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: ProfileMessageViewModel!
        var messagesDataSource: FakeMessagesDataSource!
        let dog = DogFactory().produce()

        Given("I open a dog profile") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                messagesDataSource = container~>
                viewModel = container.resolve(ProfileMessageViewModel.self, argument: dog)
            }

            justBeforeEach {
                viewModel.onViewAppear()
            }

            Then("The message bar is empty and sending is disabled") {
                expect(viewModel.state) == ProfileMessageViewModel.State(text: "", toast: nil)
                expect(viewModel.state.isSendEnabled) == false
            }

            When("I type a message") {
                justBeforeEach {
                    viewModel.onTextChange("Wanna play fetch?")
                }

                Then("Sending is enabled") {
                    expect(viewModel.state.text) == "Wanna play fetch?"
                    expect(viewModel.state.isSendEnabled) == true
                }

                And("I tap send") {
                    justBeforeEach {
                        viewModel.onSendTap()
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("The message is sent, the bar is cleared, and a confirmation is shown") {
                        expect(messagesDataSource.sentMessages) == [SentMessage(dogId: 1, text: "Wanna play fetch?")]
                        expect(viewModel.state) == ProfileMessageViewModel.State(
                            text: "",
                            toast: .messageSent(name: "Bruno")
                        )
                    }
                }

                And("Sending fails") {
                    beforeEach {
                        MessagesDataSourceFactory(container).withSendMessageError()
                    }

                    justBeforeEach {
                        viewModel.onSendTap()
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("The typed message is kept and an error is emitted") {
                        expect(viewModel.state.text) == "Wanna play fetch?"
                        expect(viewModel.error).toNot(beNil())
                    }
                }
            }

            When("I send a blank message") {
                justBeforeEach {
                    viewModel.onTextChange("   ")
                    viewModel.onSendTap()
                    TimeAdvancingFactory(container).tick()
                }

                Then("Nothing is sent and an empty message error is emitted") {
                    expect(messagesDataSource.sentMessages) == []
                    expect(viewModel.error) == .emptyMessage
                }
            }
        }
    }
}
