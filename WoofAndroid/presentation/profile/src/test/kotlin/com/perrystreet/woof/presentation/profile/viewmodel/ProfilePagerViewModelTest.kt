package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.presentation.profile.factory.DogsFeedFactory
import com.perrystreet.woof.presentation.profile.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.profile.utils.UiObservableExtensions.test
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import org.amshove.kluent.shouldBeEqualTo
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ProfilePagerViewModelTest : ViewModelBehaviorSpec() {
    private val viewModel: ProfilePagerViewModel by inject { parametersOf(3L) }

    init {
        Given("I open the profile of the third dog in the grid") {
            TimeAdvancingFactory().withTestSchedulers()
            DogsFeedFactory().withLoadedDogs(count = 5)

            beforeEach {
                viewModel.onViewAppear()
            }

            Then("The pager shows every loaded dog starting from the tapped one") {
                val state = viewModel.state.test().lastValue()
                state.pages.map { it.name } shouldBeEqualTo listOf("Dog 1", "Dog 2", "Dog 3", "Dog 4", "Dog 5")
                state.initialPage shouldBeEqualTo 2
            }

            When("I block the fourth dog") {
                beforeEach {
                    val fourthDog = viewModel.state.test().lastValue().pages[3].domain
                    val moderationViewModel: ProfileModerationViewModel by inject { parametersOf(fourthDog) }
                    moderationViewModel.onBlockTap()
                    moderationViewModel.onDialogConfirm()
                    TimeAdvancingFactory().tick()
                }

                Then("The blocked dog disappears from the pager") {
                    val state = viewModel.state.test().lastValue()
                    state.pages.map { it.name } shouldBeEqualTo listOf("Dog 1", "Dog 2", "Dog 3", "Dog 5")
                }
            }
        }
    }
}
