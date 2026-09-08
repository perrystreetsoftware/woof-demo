import Combine
import DI
import PresentationCommon

@Factory
public final class HomeViewModel: StateProducingViewModel<HomeViewModel.State, Never> {
    init() {
        super.init(initialValue: .initial)
    }

    public func onTabSelect(_ tab: HomeTabUIModel) {
        setState(currentState.copy(selectedTab: tab))
    }

    public func onBackTap() {
        setState(currentState.copy(selectedTab: .browse))
    }
}

extension HomeViewModel {
    public struct State: Equatable {
        public let selectedTab: HomeTabUIModel
        public let tabs: [HomeTabUIModel]

        public var isBackHandled: Bool {
            selectedTab != .browse
        }

        public static let initial = State(selectedTab: .browse, tabs: HomeTabUIModel.allCases)

        public init(selectedTab: HomeTabUIModel, tabs: [HomeTabUIModel]) {
            self.selectedTab = selectedTab
            self.tabs = tabs
        }

        func copy(selectedTab: HomeTabUIModel) -> State {
            State(selectedTab: selectedTab, tabs: tabs)
        }
    }
}
