package com.perrystreet.woof.presentation.favorites.viewmodel

import com.perrystreet.woof.datasource.dogs.DogsDataSourceFactory
import com.perrystreet.woof.presentation.favorites.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.favorites.utils.UiObservableExtensions.test
import com.perrystreet.woof.repositories.dogs.DogsRepository
import com.perrystreet.woof.repositories.favorites.FavoritesRepository
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import io.kotest.matchers.types.shouldBeInstanceOf
import org.amshove.kluent.shouldBeEqualTo
import org.koin.test.inject

class FavoritesViewModelTest : ViewModelBehaviorSpec() {
    private val viewModel: FavoritesViewModel by inject()
    private val dogsRepository: DogsRepository by inject()
    private val favoritesRepository: FavoritesRepository by inject()

    init {
        Given("I open the favorites tab with dogs loaded in the grid") {
            TimeAdvancingFactory().withTestSchedulers()
            DogsDataSourceFactory().withDogs(count = 5)

            beforeEach {
                dogsRepository.getDogs(offset = 0, limit = 5).test()
                viewModel.onViewAppear()
                TimeAdvancingFactory().tick()
            }

            Then("The favorites are empty") {
                viewModel.state.test().lastValue() shouldBeEqualTo FavoritesViewModel.State.Empty
            }

            When("I favorite two dogs") {
                beforeEach {
                    val dogs = dogsRepository.dogsFeed.test().lastValue().dogs
                    favoritesRepository.addFavorite(dogs[3])
                    favoritesRepository.addFavorite(dogs[1])
                    TimeAdvancingFactory().tick()
                }

                Then("The favorites grid shows them in grid order") {
                    val state = viewModel.state.test().lastValue().shouldBeInstanceOf<FavoritesViewModel.State.Loaded>()
                    state.cells.map { it.name } shouldBeEqualTo listOf("Dog 2", "Dog 4")
                }

                And("I remove one favorite") {
                    beforeEach {
                        val dogs = dogsRepository.dogsFeed.test().lastValue().dogs
                        favoritesRepository.removeFavorite(dogs[1])
                        TimeAdvancingFactory().tick()
                    }

                    Then("Only the remaining favorite is shown") {
                        val state = viewModel.state.test().lastValue().shouldBeInstanceOf<FavoritesViewModel.State.Loaded>()
                        state.cells.map { it.name } shouldBeEqualTo listOf("Dog 4")
                    }
                }
            }
        }
    }
}
