import Combine
import DataSourceFakes
import Nimble
import PresentationFavorites
import Quick
import Repositories
import Swinject
import SwinjectAutoregistration
import TestUtils
import UtilsTestExtensions

final class FavoritesViewModelTest: QuickSpec {
    override class func spec() {
        var container: Container!
        var viewModel: FavoritesViewModel!
        var dogsRepository: DogsRepository!
        var favoritesRepository: FavoritesRepository!
        var cancellables = Set<AnyCancellable>()

        Given("I open the favorites tab with dogs loaded in the grid") {
            beforeEach {
                container = Container().injectForViewModelTests()
                TimeAdvancingFactory(container).withTestSchedulers()
                DogsDataSourceFactory(container).withDogs(count: 5)
                dogsRepository = container~>
                favoritesRepository = container~>
                viewModel = container~>
            }

            justBeforeEach {
                dogsRepository.getDogs(offset: 0, limit: 5).pss_sink { _ in }.store(in: &cancellables)
                viewModel.onViewAppear()
                TimeAdvancingFactory(container).tick()
            }

            Then("The favorites are empty") {
                expect(viewModel.state) == .empty
            }

            When("I favorite two dogs") {
                justBeforeEach {
                    let dogs = dogsRepository.dogsFeed.record().lastValue().dogs
                    favoritesRepository.addFavorite(dog: dogs[3]).pss_sink { _ in }.store(in: &cancellables)
                    favoritesRepository.addFavorite(dog: dogs[1]).pss_sink { _ in }.store(in: &cancellables)
                    TimeAdvancingFactory(container).tick()
                }

                Then("The favorites grid shows them in grid order") {
                    guard case .loaded(let cells) = viewModel.state else { return fail("Expected loaded state") }
                    expect(cells.map(\.name)) == ["Dog 2", "Dog 4"]
                }

                And("I remove one favorite") {
                    justBeforeEach {
                        let dogs = dogsRepository.dogsFeed.record().lastValue().dogs
                        favoritesRepository.removeFavorite(dog: dogs[1]).pss_sink { _ in }.store(in: &cancellables)
                        TimeAdvancingFactory(container).tick()
                    }

                    Then("Only the remaining favorite is shown") {
                        guard case .loaded(let cells) = viewModel.state else { return fail("Expected loaded state") }
                        expect(cells.map(\.name)) == ["Dog 4"]
                    }
                }
            }
        }
    }
}
