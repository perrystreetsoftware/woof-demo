import Combine
import DI
import Models
import PresentationCommon
import UseCase
import Utils

@Factory
public final class GridViewModel: StateProducingViewModel<GridViewModel.State, DogsError> {
    private let getDogsFeedUseCase: GetDogsFeedUseCase
    private let loadNextDogsPageUseCase: LoadNextDogsPageUseCase
    private let cellMapper: DogDomainToCellUIModelMapper

    private var isLoadingPage = false

    private static let loadMoreThreshold = 12
    static let loadingMoreCellCount = 6

    init(
        getDogsFeedUseCase: GetDogsFeedUseCase,
        loadNextDogsPageUseCase: LoadNextDogsPageUseCase,
        cellMapper: DogDomainToCellUIModelMapper
    ) {
        self.getDogsFeedUseCase = getDogsFeedUseCase
        self.loadNextDogsPageUseCase = loadNextDogsPageUseCase
        self.cellMapper = cellMapper
        super.init(initialValue: .loading)
    }

    public override func onFirstAppear() {
        getDogsFeedUseCase()
            .sink { [weak self] feed in self?.onFeedUpdate(feed) }
            .store(in: &cancellables)
        loadNextPage()
    }

    public func onCellAppear(_ cell: DogCellUIModel) {
        guard case .loaded(let loaded) = currentState, loaded.hasMore, !isLoadingPage else { return }
        guard cell.index >= loaded.cells.count - Self.loadMoreThreshold else { return }
        loadNextPage()
    }

    public func onRetryTap() {
        setState(.loading)
        loadNextPage()
    }

    private func onFeedUpdate(_ feed: DogsFeed) {
        guard !feed.dogs.isEmpty else { return }
        setState(
            .loaded(
                State.Loaded(
                    cells: feed.dogs.enumerated().map { index, dog in cellMapper(dog: dog, index: index) },
                    isLoadingMore: isLoadingPage,
                    hasMore: feed.hasMore
                )
            )
        )
    }

    private func loadNextPage() {
        isLoadingPage = true
        updateLoadingMore()
        loadNextDogsPageUseCase()
            .pss_sink { [weak self] result in
                guard let self else { return }
                switch result {
                case .success:
                    isLoadingPage = false
                    updateLoadingMore()
                case .failure(let error):
                    isLoadingPage = false
                    onPageError(error)
                }
            }
            .store(in: &cancellables)
    }

    private func updateLoadingMore() {
        guard case .loaded(let loaded) = currentState else { return }
        setState(.loaded(loaded.copy(isLoadingMore: isLoadingPage)))
    }

    private func onPageError(_ error: DogsError) {
        switch currentState {
        case .loaded:
            updateLoadingMore()
            emitError(error)
        case .loading, .error:
            setState(.error)
        }
    }
}

extension GridViewModel {
    public enum State: Equatable {
        case loading
        case loaded(Loaded)
        case error

        public struct Loaded: Equatable {
            public let cells: [DogCellUIModel]
            public let isLoadingMore: Bool
            public let hasMore: Bool

            public var loadingMoreCellCount: Int {
                switch isLoadingMore {
                case true: GridViewModel.loadingMoreCellCount
                case false: 0
                }
            }

            public init(cells: [DogCellUIModel], isLoadingMore: Bool, hasMore: Bool) {
                self.cells = cells
                self.isLoadingMore = isLoadingMore
                self.hasMore = hasMore
            }

            func copy(isLoadingMore: Bool) -> Loaded {
                Loaded(cells: cells, isLoadingMore: isLoadingMore, hasMore: hasMore)
            }
        }
    }
}
