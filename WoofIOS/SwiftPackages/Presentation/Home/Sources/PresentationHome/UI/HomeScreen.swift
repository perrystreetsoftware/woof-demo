import DesignSystem
import Resources
import SwiftUI

public struct HomeScreen<TabContent: View>: View {
    private let state: HomeViewModel.State
    private let onTabSelect: (HomeTabUIModel) -> Void
    private let tabContent: (HomeTabUIModel) -> TabContent

    public init(
        state: HomeViewModel.State,
        onTabSelect: @escaping (HomeTabUIModel) -> Void,
        @ViewBuilder tabContent: @escaping (HomeTabUIModel) -> TabContent
    ) {
        self.state = state
        self.onTabSelect = onTabSelect
        self.tabContent = tabContent
    }

    public var body: some View {
        TemplateBottomNavigation(
            selectedTab: state.selectedTab,
            items: state.tabs.map { tab in
                let isSelected = tab == state.selectedTab
                return OrgBottomNavigationItem(
                    icon: tab.icon(isSelected: isSelected),
                    label: tab.label,
                    value: tab,
                    isSelected: isSelected,
                    onTap: { onTabSelect(tab) }
                )
            },
            onTabSelect: onTabSelect,
            content: tabContent
        )
    }
}

#Preview("Home") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        HomeScreen(state: .initial, onTabSelect: { _ in }) { tab in
            OrgEmptyState(title: L10n.appName, message: String(describing: tab))
        }
    }
}
