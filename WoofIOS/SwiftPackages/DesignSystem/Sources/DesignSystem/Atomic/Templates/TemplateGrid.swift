import SwiftUI

public struct TemplateGrid<TopBar: View, Content: View>: View {
    @Environment(\.theme) private var theme

    private let topBar: () -> TopBar
    private let content: () -> Content

    public init(@ViewBuilder topBar: @escaping () -> TopBar, @ViewBuilder content: @escaping () -> Content) {
        self.topBar = topBar
        self.content = content
    }

    public var body: some View {
        VStack(spacing: 0) {
            topBar()
                .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
            ScrollView {
                LazyVGrid(columns: columns, spacing: SpacingRoles.Component.extraCompact.rawValue) {
                    content()
                }
                .padding(PaddingRoles.Screen.extraCompact.rawValue)
            }
            .scrollIndicators(.hidden)
        }
        .background(theme.colors.background.ignoresSafeArea())
    }

    private var columns: [GridItem] {
        [
            GridItem(
                .adaptive(minimum: SizingRoles.GridCell.minWidth),
                spacing: SpacingRoles.Component.extraCompact.rawValue
            )
        ]
    }
}
