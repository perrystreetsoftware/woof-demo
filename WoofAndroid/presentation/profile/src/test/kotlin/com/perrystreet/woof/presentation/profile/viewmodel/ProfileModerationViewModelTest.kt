package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.datasource.moderation.FakeModerationDataSource
import com.perrystreet.woof.datasource.moderation.ModerationDataSourceFactory
import com.perrystreet.woof.presentation.profile.factory.DogFactory
import com.perrystreet.woof.presentation.profile.navigation.FakeNavigator
import com.perrystreet.woof.presentation.profile.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.profile.uimodel.ProfileModerationDialogUIModel
import com.perrystreet.woof.presentation.profile.uimodel.ProfileToastUIModel
import com.perrystreet.woof.presentation.profile.utils.UiObservableExtensions.test
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import org.amshove.kluent.shouldBeEqualTo
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ProfileModerationViewModelTest : ViewModelBehaviorSpec() {
    private val dog = DogFactory().produce()
    private val viewModel: ProfileModerationViewModel by inject { parametersOf(dog) }
    private val moderationDataSource: FakeModerationDataSource by inject()
    private val navigator: FakeNavigator by inject()

    init {
        Given("I open a dog profile") {
            TimeAdvancingFactory().withTestSchedulers()

            beforeEach {
                viewModel.onViewAppear()
            }

            Then("No dialog is shown") {
                viewModel.state.test().lastValue() shouldBeEqualTo ProfileModerationViewModel.State.Initial
            }

            When("I choose to report the dog") {
                beforeEach {
                    viewModel.onReportTap()
                }

                Then("The report confirmation dialog is shown") {
                    viewModel.state.test().lastValue().dialog shouldBeEqualTo ProfileModerationDialogUIModel.Report("Bruno")
                }

                And("I dismiss the dialog") {
                    beforeEach {
                        viewModel.onDialogDismiss()
                    }

                    Then("Nothing is reported") {
                        viewModel.state.test().lastValue() shouldBeEqualTo ProfileModerationViewModel.State.Initial
                        moderationDataSource.reportedDogIds shouldBeEqualTo emptyList()
                    }
                }

                And("I confirm the dialog") {
                    beforeEach {
                        viewModel.onDialogConfirm()
                        TimeAdvancingFactory().tick()
                    }

                    Then("The dog is reported and a confirmation is shown") {
                        moderationDataSource.reportedDogIds shouldBeEqualTo listOf(1L)
                        viewModel.state.test().lastValue() shouldBeEqualTo ProfileModerationViewModel.State(
                            dialog = null,
                            toast = ProfileToastUIModel.ReportSent,
                        )
                    }
                }
            }

            When("I choose to block the dog and confirm") {
                beforeEach {
                    viewModel.onBlockTap()
                    viewModel.onDialogConfirm()
                    TimeAdvancingFactory().tick()
                }

                Then("The dog is blocked and I am taken back to the grid") {
                    moderationDataSource.blockedDogIds shouldBeEqualTo listOf(1L)
                    viewModel.state.test().lastValue() shouldBeEqualTo ProfileModerationViewModel.State.Initial
                    navigator.backCount shouldBeEqualTo 1
                }
            }

            When("Blocking fails") {
                ModerationDataSourceFactory().withBlockDogError()

                beforeEach {
                    viewModel.onBlockTap()
                    viewModel.onDialogConfirm()
                    TimeAdvancingFactory().tick()
                }

                Then("I stay on the profile and an error is emitted") {
                    navigator.backCount shouldBeEqualTo 0
                    viewModel.error.test().lastValue().isPresent shouldBeEqualTo true
                }
            }
        }
    }
}
