import Combine
import DI
import Models
import PresentationCommon
import UseCase
import Utils

@Factory
public final class ProfileHeaderViewModel: StateDerivingViewModel<ProfileHeaderViewModel.State, FavoritesError> {
    @DIArgument private let dog: Dog
    private let toggleDogFavoriteUseCase: ToggleDogFavoriteUseCase
    private let overflowExpanded: CurrentValueSubject<Bool, Never>

    init(
        dog: Dog,
        toggleDogFavoriteUseCase: ToggleDogFavoriteUseCase,
        isDogFavoriteUseCase: IsDogFavoriteUseCase
    ) {
        let overflowExpanded = CurrentValueSubject<Bool, Never>(false)
        self.dog = dog
        self.toggleDogFavoriteUseCase = toggleDogFavoriteUseCase
        self.overflowExpanded = overflowExpanded
        super.init(
            initialValue: State(
                name: dog.name,
                isFavorite: false,
                isOverflowExpanded: false,
                overflowItems: ProfileOverflowMenuItemUIModel.allCases
            ),
            source: Publishers.CombineLatest(
                isDogFavoriteUseCase(dog: dog),
                overflowExpanded
            )
            .map { isFavorite, isOverflowExpanded in
                State(
                    name: dog.name,
                    isFavorite: isFavorite,
                    isOverflowExpanded: isOverflowExpanded,
                    overflowItems: ProfileOverflowMenuItemUIModel.allCases
                )
            }
            .eraseToAnyPublisher()
        )
    }

    public func onFavoriteTap() {
        toggleDogFavoriteUseCase(dog: dog)
            .pss_sink { [weak self] result in
                guard case .failure(let error) = result else { return }
                self?.emitError(error)
            }
            .store(in: &cancellables)
    }

    public func onOverflowExpandedChange(_ isExpanded: Bool) {
        overflowExpanded.send(isExpanded)
    }
}

extension ProfileHeaderViewModel {
    public struct State: Equatable {
        public let name: String
        public let isFavorite: Bool
        public let isOverflowExpanded: Bool
        public let overflowItems: [ProfileOverflowMenuItemUIModel]

        public init(name: String, isFavorite: Bool, isOverflowExpanded: Bool, overflowItems: [ProfileOverflowMenuItemUIModel]) {
            self.name = name
            self.isFavorite = isFavorite
            self.isOverflowExpanded = isOverflowExpanded
            self.overflowItems = overflowItems
        }
    }
}
