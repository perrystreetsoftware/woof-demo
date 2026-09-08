import SwiftUI

public struct TemplateScrollableContent<TopBar: View, Content: View>: View {
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
                LazyVStack(alignment: .leading, spacing: SpacingRoles.Module.compact.rawValue) {
                    content()
                }
                .padding(.horizontal, theme.padding.screenHorizontal)
                .padding(.top, theme.padding.elementRegular)
                .padding(.bottom, theme.padding.screenBottomRegular)
            }
            .scrollIndicators(.hidden)
        }
        .background(theme.colors.background.ignoresSafeArea())
    }
}
