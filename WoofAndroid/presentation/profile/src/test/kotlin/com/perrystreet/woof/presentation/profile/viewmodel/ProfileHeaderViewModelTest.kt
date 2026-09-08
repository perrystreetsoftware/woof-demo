package com.perrystreet.woof.presentation.profile.viewmodel

import com.perrystreet.woof.datasource.favorites.FavoritesDataSourceFactory
import com.perrystreet.woof.presentation.profile.factory.DogFactory
import com.perrystreet.woof.presentation.profile.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.profile.uimodel.ProfileOverflowMenuItemUIModel
import com.perrystreet.woof.presentation.profile.utils.UiObservableExtensions.test
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import org.amshove.kluent.shouldBeEqualTo
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

class ProfileHeaderViewModelTest : ViewModelBehaviorSpec() {
    private val dog = DogFactory().produce()
    private val viewModel: ProfileHeaderViewModel by inject { parametersOf(dog) }

    init {
        Given("I open a dog profile") {
            TimeAdvancingFactory().withTestSchedulers()

            beforeEach {
                viewModel.onViewAppear()
            }

            Then("The header shows the name, no favorite, and the closed overflow menu") {
                viewModel.state.test().lastValue() shouldBeEqualTo ProfileHeaderViewModel.State(
                    name = "Bruno",
                    isFavorite = false,
                    isOverflowExpanded = false,
                    overflowItems = listOf(ProfileOverflowMenuItemUIModel.Report, ProfileOverflowMenuItemUIModel.Block),
                )
            }

            When("I tap the favorite button") {
                beforeEach {
                    viewModel.onFavoriteTap()
                    TimeAdvancingFactory().tick()
                }

                Then("The dog becomes a favorite") {
                    viewModel.state.test().lastValue().isFavorite shouldBeEqualTo true
                }

                And("I tap the favorite button again") {
                    beforeEach {
                        viewModel.onFavoriteTap()
                        TimeAdvancingFactory().tick()
                    }

                    Then("The dog is no longer a favorite") {
                        viewModel.state.test().lastValue().isFavorite shouldBeEqualTo false
                    }
                }
            }

            When("Adding a favorite fails") {
                FavoritesDataSourceFactory().withAddFavoriteError()

                beforeEach {
                    viewModel.onFavoriteTap()
                    TimeAdvancingFactory().tick()
                }

                Then("The favorite is rolled back and an error is emitted") {
                    viewModel.state.test().lastValue().isFavorite shouldBeEqualTo false
                    viewModel.error.test().lastValue().isPresent shouldBeEqualTo true
                }
            }

            When("I open the overflow menu") {
                beforeEach {
                    viewModel.onOverflowExpandedChange(true)
                }

                Then("The overflow menu is expanded") {
                    viewModel.state.test().lastValue().isOverflowExpanded shouldBeEqualTo true
                }
            }
        }
    }
}
