import SwiftUI

public struct TemplateBottomNavigation<Value: Hashable, Content: View>: View {
    @Environment(\.theme) private var theme

    private let selectedTab: Value
    private let items: [OrgBottomNavigationItem<Value>]
    private let onTabSelect: (Value) -> Void
    private let content: (Value) -> Content

    public init(
        selectedTab: Value,
        items: [OrgBottomNavigationItem<Value>],
        onTabSelect: @escaping (Value) -> Void,
        @ViewBuilder content: @escaping (Value) -> Content
    ) {
        self.selectedTab = selectedTab
        self.items = items
        self.onTabSelect = onTabSelect
        self.content = content
    }

    public var body: some View {
        TabView(selection: Binding(get: { selectedTab }, set: onTabSelect)) {
            ForEach(items) { item in
                Tab(value: item.value) {
                    content(item.value)
                } label: {
                    OrgBottomNavigationBarLabel(role: item.role, isSelected: item.isSelected)
                }
            }
        }
        .tint(theme.colors.primary)
        .background(theme.colors.background)
    }
}
