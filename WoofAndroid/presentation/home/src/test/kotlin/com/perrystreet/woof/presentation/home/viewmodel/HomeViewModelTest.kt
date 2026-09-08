package com.perrystreet.woof.presentation.home.viewmodel

import com.perrystreet.woof.presentation.home.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.home.uimodel.HomeTabUIModel
import com.perrystreet.woof.presentation.home.utils.UiObservableExtensions.test
import com.perrystreet.woof.testutils.rx.lastValue
import org.amshove.kluent.shouldBeEqualTo
import org.koin.test.inject

class HomeViewModelTest : ViewModelBehaviorSpec() {
    private val viewModel: HomeViewModel by inject()

    init {
        Given("I open the app") {
            beforeEach {
                viewModel.onViewAppear()
            }

            Then("The browse tab is selected and back is not intercepted") {
                val state = viewModel.state.test().lastValue()
                state.selectedTab shouldBeEqualTo HomeTabUIModel.Browse
                state.tabs shouldBeEqualTo listOf(HomeTabUIModel.Browse, HomeTabUIModel.Favorites, HomeTabUIModel.Account)
                state.isBackHandled shouldBeEqualTo false
            }

            When("I select the favorites tab") {
                beforeEach {
                    viewModel.onTabSelect(HomeTabUIModel.Favorites)
                }

                Then("The favorites tab is selected and back is intercepted") {
                    val state = viewModel.state.test().lastValue()
                    state.selectedTab shouldBeEqualTo HomeTabUIModel.Favorites
                    state.isBackHandled shouldBeEqualTo true
                }

                And("I press back") {
                    beforeEach {
                        viewModel.onBackTap()
                    }

                    Then("The browse tab is selected again") {
                        viewModel.state.test().lastValue().selectedTab shouldBeEqualTo HomeTabUIModel.Browse
                    }
                }
            }
        }
    }
}
