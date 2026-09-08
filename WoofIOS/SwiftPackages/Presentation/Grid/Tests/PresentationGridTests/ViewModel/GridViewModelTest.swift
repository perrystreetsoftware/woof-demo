import DataSourceFakes
import Nimble
import PresentationGrid
import Quick
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class GridViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: GridViewModel!

        Given("I open the grid") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                viewModel = container~>
            }

            Then("The grid shows the loading shimmer") {
                expect(viewModel.state) == .loading
            }

            When("The first page of dogs loads") {
                beforeEach {
                    DogsDataSourceFactory(container).withDogs(count: 450)
                }

                justBeforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The grid shows the first 200 dogs and can load more") {
                    guard case .loaded(let state) = viewModel.state else { return fail("Expected loaded state") }
                    expect(state.cells.count) == 200
                    expect(state.cells.first?.name) == "Dog 1"
                    expect(state.isLoadingMore) == false
                    expect(state.hasMore) == true
                    expect(state.loadingMoreCellCount) == 0
                }

                And("A cell far from the end appears") {
                    justBeforeEach {
                        guard case .loaded(let loaded) = viewModel.state else { return }
                        viewModel.onCellAppear(loaded.cells[100])
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("No extra page is requested") {
                        guard case .loaded(let state) = viewModel.state else { return fail("Expected loaded state") }
                        expect(state.cells.count) == 200
                    }
                }

                And("A cell near the end appears") {
                    justBeforeEach {
                        guard case .loaded(let loaded) = viewModel.state else { return }
                        viewModel.onCellAppear(loaded.cells.last!)
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("The next page is appended to the grid") {
                        guard case .loaded(let state) = viewModel.state else { return fail("Expected loaded state") }
                        expect(state.cells.count) == 400
                        expect(state.hasMore) == true
                    }

                    And("The last cell of the second page appears") {
                        justBeforeEach {
                            guard case .loaded(let loaded) = viewModel.state else { return }
                            viewModel.onCellAppear(loaded.cells.last!)
                            TimeAdvancingFactory(container).tick()
                        }

                        Then("The remaining dogs are appended and there is nothing more to load") {
                            guard case .loaded(let state) = viewModel.state else { return fail("Expected loaded state") }
                            expect(state.cells.count) == 450
                            expect(state.hasMore) == false
                        }
                    }
                }
            }

            When("The first page fails to load") {
                beforeEach {
                    DogsDataSourceFactory(container).withDogsError()
                }

                justBeforeEach {
                    viewModel.onViewAppear()
                    TimeAdvancingFactory(container).tick()
                }

                Then("The grid shows the error state") {
                    expect(viewModel.state) == .error
                }

                And("I tap retry after the connection recovers") {
                    justBeforeEach {
                        DogsDataSourceFactory(container).withDogs(count: 10).withoutErrors()
                        viewModel.onRetryTap()
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("The grid shows the dogs") {
                        guard case .loaded(let state) = viewModel.state else { return fail("Expected loaded state") }
                        expect(state.cells.count) == 10
                        expect(state.hasMore) == false
                    }
                }
            }
        }
    }
}
