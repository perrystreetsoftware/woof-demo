package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.datasource.messages.FakeMessagesDataSource
import com.perrystreet.woof.datasource.messages.MessagesDataSourceFactory
import com.perrystreet.woof.models.errors.MessageException
import com.perrystreet.woof.presentation.profile.factory.DogFactory
import com.perrystreet.woof.presentation.profile.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.profile.uimodel.ProfileToastUIModel
import com.perrystreet.woof.presentation.profile.utils.UiObservableExtensions.test
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import org.amshove.kluent.shouldBeEqualTo
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ProfileMessageViewModelTest : ViewModelBehaviorSpec() {
    private val dog = DogFactory().produce()
    private val viewModel: ProfileMessageViewModel by inject { parametersOf(dog) }
    private val messagesDataSource: FakeMessagesDataSource by inject()

    init {
        Given("I open a dog profile") {
            TimeAdvancingFactory().withTestSchedulers()

            beforeEach {
                viewModel.onViewAppear()
            }

            Then("The message bar is empty and sending is disabled") {
                val state = viewModel.state.test().lastValue()
                state shouldBeEqualTo ProfileMessageViewModel.State(text = "", toast = null)
                state.isSendEnabled shouldBeEqualTo false
            }

            When("I type a message") {
                beforeEach {
                    viewModel.onTextChange("Wanna play fetch?")
                }

                Then("Sending is enabled") {
                    val state = viewModel.state.test().lastValue()
                    state.text shouldBeEqualTo "Wanna play fetch?"
                    state.isSendEnabled shouldBeEqualTo true
                }

                And("I tap send") {
                    beforeEach {
                        viewModel.onSendTap()
                        TimeAdvancingFactory().tick()
                    }

                    Then("The message is sent, the bar is cleared, and a confirmation is shown") {
                        messagesDataSource.sentMessages shouldBeEqualTo listOf(1L to "Wanna play fetch?")
                        viewModel.state.test().lastValue() shouldBeEqualTo ProfileMessageViewModel.State(
                            text = "",
                            toast = ProfileToastUIModel.MessageSent("Bruno"),
                        )
                    }
                }

                And("Sending fails") {
                    MessagesDataSourceFactory().withSendMessageError()

                    beforeEach {
                        viewModel.onSendTap()
                        TimeAdvancingFactory().tick()
                    }

                    Then("The typed message is kept and an error is emitted") {
                        viewModel.state.test().lastValue().text shouldBeEqualTo "Wanna play fetch?"
                        viewModel.error.test().lastValue().isPresent shouldBeEqualTo true
                    }
                }
            }

            When("I send a blank message") {
                beforeEach {
                    viewModel.onTextChange("   ")
                    viewModel.onSendTap()
                    TimeAdvancingFactory().tick()
                }

                Then("Nothing is sent and an empty message error is emitted") {
                    messagesDataSource.sentMessages shouldBeEqualTo emptyList()
                    viewModel.error.test().lastValue().get() shouldBeEqualTo MessageException.EmptyMessage
                }
            }
        }
    }
}
