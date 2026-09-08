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
            ScrollView {
                LazyVGrid(columns: columns, spacing: SpacingRoles.Component.extraCompact.rawValue) {
                    content()
                }
                .padding(.horizontal, theme.padding.elementCompact)
                .padding(.bottom, theme.padding.screenBottomRegular)
            }
            .scrollIndicators(.hidden)
        }
        .background(theme.colors.background.ignoresSafeArea())
    }

    private var columns: [GridItem] {
        Array(
            repeating: GridItem(.flexible(), spacing: SpacingRoles.Component.extraCompact.rawValue),
            count: theme.sizing.gridColumns
        )
    }
}
