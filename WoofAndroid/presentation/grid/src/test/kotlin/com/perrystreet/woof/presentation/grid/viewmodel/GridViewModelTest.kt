package com.perrystreet.woof.presentation.grid.viewmodel

import com.perrystreet.woof.datasource.dogs.DogsDataSourceFactory
import com.perrystreet.woof.presentation.grid.spec.ViewModelBehaviorSpec
import com.perrystreet.woof.presentation.grid.utils.UiObservableExtensions.test
import com.perrystreet.woof.presentation.grid.viewmodel.GridViewModel.State
import com.perrystreet.woof.testutils.rx.lastValue
import com.perrystreet.woof.testutils.scheduler.TimeAdvancingFactory
import io.kotest.matchers.types.shouldBeInstanceOf
import org.amshove.kluent.shouldBeEqualTo
import org.koin.test.inject

class GridViewModelTest : ViewModelBehaviorSpec() {
    private val viewModel: GridViewModel by inject()

    init {
        Given("I open the grid") {
            TimeAdvancingFactory().withTestSchedulers()

            Then("The grid shows the loading shimmer") {
                viewModel.state.test().lastValue() shouldBeEqualTo State.Loading
            }

            When("The first page of dogs loads") {
                DogsDataSourceFactory().withDogs(count = 450)

                beforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory().tick()
                }

                Then("The grid shows the first 200 dogs and can load more") {
                    val state = viewModel.state.test().lastValue().shouldBeInstanceOf<State.Loaded>()
                    state.cells.size shouldBeEqualTo 200
                    state.cells.first().name shouldBeEqualTo "Dog 1"
                    state.isLoadingMore shouldBeEqualTo false
                    state.hasMore shouldBeEqualTo true
                    state.loadingMoreCellCount shouldBeEqualTo 0
                }

                And("A cell far from the end appears") {
                    beforeEach {
                        val loaded = viewModel.state.test().lastValue() as State.Loaded
                        viewModel.onCellAppear(loaded.cells[100])
                        TimeAdvancingFactory().tick()
                    }

                    Then("No extra page is requested") {
                        val state = viewModel.state.test().lastValue().shouldBeInstanceOf<State.Loaded>()
                        state.cells.size shouldBeEqualTo 200
                    }
                }

                And("A cell near the end appears") {
                    beforeEach {
                        val loaded = viewModel.state.test().lastValue() as State.Loaded
                        viewModel.onCellAppear(loaded.cells.last())
                        TimeAdvancingFactory().tick()
                    }

                    Then("The next page is appended to the grid") {
                        val state = viewModel.state.test().lastValue().shouldBeInstanceOf<State.Loaded>()
                        state.cells.size shouldBeEqualTo 400
                        state.hasMore shouldBeEqualTo true
                    }

                    And("The last cell of the second page appears") {
                        beforeEach {
                            val loaded = viewModel.state.test().lastValue() as State.Loaded
                            viewModel.onCellAppear(loaded.cells.last())
                            TimeAdvancingFactory().tick()
                        }

                        Then("The remaining dogs are appended and there is nothing more to load") {
                            val state = viewModel.state.test().lastValue().shouldBeInstanceOf<State.Loaded>()
                            state.cells.size shouldBeEqualTo 450
                            state.hasMore shouldBeEqualTo false
                        }
                    }
                }
            }

            When("The first page fails to load") {
                DogsDataSourceFactory().withDogsError()

                beforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory().tick()
                }

                Then("The grid shows the error state") {
                    viewModel.state.test().lastValue() shouldBeEqualTo State.Error
                }

                And("I tap retry after the connection recovers") {
                    beforeEach {
                        DogsDataSourceFactory().withDogs(count = 10).withoutErrors()
                        viewModel.onRetryTap()
                        TimeAdvancingFactory().tick()
                    }

                    Then("The grid shows the dogs") {
                        val state = viewModel.state.test().lastValue().shouldBeInstanceOf<State.Loaded>()
                        state.cells.size shouldBeEqualTo 10
                        state.hasMore shouldBeEqualTo false
                    }
                }
            }
        }
    }
}
