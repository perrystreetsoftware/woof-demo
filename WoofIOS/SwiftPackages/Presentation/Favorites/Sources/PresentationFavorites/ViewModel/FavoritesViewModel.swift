import Combine
import DI
import Models
import PresentationCommon
import UseCase

@Factory
public final class FavoritesViewModel: StateDerivingViewModel<FavoritesViewModel.State, Never> {
    private let cellMapper: DogDomainToFavoriteCellUIModelMapper

    init(
        cellMapper: DogDomainToFavoriteCellUIModelMapper,
        getFavoriteDogsUseCase: GetFavoriteDogsUseCase
    ) {
        self.cellMapper = cellMapper
        super.init(
            initialValue: .empty,
            source: getFavoriteDogsUseCase()
                .map { dogs in
                    switch dogs.isEmpty {
                    case true: State.empty
                    case false: State.loaded(cells: dogs.map { cellMapper(dog: $0) })
                    }
                }
                .eraseToAnyPublisher()
        )
    }
}

extension FavoritesViewModel {
    public enum State: Equatable {
        case empty
        case loaded(cells: [FavoriteCellUIModel])
    }
}
