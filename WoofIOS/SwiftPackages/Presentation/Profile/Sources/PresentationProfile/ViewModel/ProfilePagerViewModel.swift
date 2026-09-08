import Combine
import DI
import Models
import PresentationCommon
import UseCase

@Factory
public final class ProfilePagerViewModel: StateDerivingViewModel<ProfilePagerViewModel.State, Never> {
    @DIArgument private let dogId: Int
    private let pageMapper: DogDomainToProfilePageUIModelMapper

    init(
        dogId: Int,
        pageMapper: DogDomainToProfilePageUIModelMapper,
        getDogsFeedUseCase: GetDogsFeedUseCase
    ) {
        self.dogId = dogId
        self.pageMapper = pageMapper
        super.init(
            initialValue: .empty,
            source: getDogsFeedUseCase()
                .map { feed in
                    let pages = feed.dogs.map { pageMapper(dog: $0) }
                    return State(
                        pages: pages,
                        initialPage: max(pages.firstIndex { $0.id == dogId } ?? 0, 0)
                    )
                }
                .removeDuplicates()
                .eraseToAnyPublisher()
        )
    }
}

extension ProfilePagerViewModel {
    public struct State: Equatable {
        public let pages: [ProfilePageUIModel]
        public let initialPage: Int

        public static let empty = State(pages: [], initialPage: 0)

        public init(pages: [ProfilePageUIModel], initialPage: Int) {
            self.pages = pages
            self.initialPage = initialPage
        }
    }
}
