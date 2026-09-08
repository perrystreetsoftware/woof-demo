package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.datasource.woofs.WoofsDataSourceFactory
import com.perrystreet.woof.models.errors.WoofException
import com.perrystreet.woof.presentation.profile.factory.DogFactory
import com.perrystreet.woof.presentation.profile.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.profile.uimodel.ProfileToastUIModel
import com.perrystreet.woof.presentation.profile.utils.UiObservableExtensions.test
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import org.amshove.kluent.shouldBeEqualTo
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ProfileWoofViewModelTest : ViewModelBehaviorSpec() {
    private val dog = DogFactory().produce()
    private val viewModel: ProfileWoofViewModel by inject { parametersOf(dog) }

    init {
        Given("I open a dog profile") {
            TimeAdvancingFactory().withTestSchedulers()

            beforeEach {
                viewModel.onViewAppear()
            }

            Then("The woof button is inactive") {
                viewModel.state.test().lastValue() shouldBeEqualTo ProfileWoofViewModel.State(
                    hasWoofed = false,
                    toast = null,
                )
            }

            When("I tap the woof button") {
                beforeEach {
                    viewModel.onWoofTap()
                    TimeAdvancingFactory().tick()
                }

                Then("The woof is sent and confirmed") {
                    viewModel.state.test().lastValue() shouldBeEqualTo ProfileWoofViewModel.State(
                        hasWoofed = true,
                        toast = ProfileToastUIModel.WoofSent("Bruno"),
                    )
                }

                And("The confirmation is dismissed") {
                    beforeEach {
                        viewModel.onToastDismiss()
                    }

                    Then("The woof stays active without a confirmation") {
                        viewModel.state.test().lastValue() shouldBeEqualTo ProfileWoofViewModel.State(
                            hasWoofed = true,
                            toast = null,
                        )
                    }
                }

                And("I tap the woof button again") {
                    beforeEach {
                        viewModel.onToastDismiss()
                        viewModel.onWoofTap()
                        TimeAdvancingFactory().tick()
                    }

                    Then("An already woofed error is emitted") {
                        viewModel.error.test().lastValue().get() shouldBeEqualTo WoofException.AlreadyWoofed
                        viewModel.state.test().lastValue().toast shouldBeEqualTo null
                    }
                }
            }

            When("Sending the woof fails") {
                WoofsDataSourceFactory().withSendWoofError()

                beforeEach {
                    viewModel.onWoofTap()
                    TimeAdvancingFactory().tick()
                }

                Then("The woof button stays inactive and an error is emitted") {
                    viewModel.state.test().lastValue().hasWoofed shouldBeEqualTo false
                    viewModel.error.test().lastValue().isPresent shouldBeEqualTo true
                }
            }
        }
    }
}
