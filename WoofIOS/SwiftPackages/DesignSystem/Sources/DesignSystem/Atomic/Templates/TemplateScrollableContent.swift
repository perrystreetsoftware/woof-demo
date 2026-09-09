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
                .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
            ScrollView {
                LazyVStack(alignment: .leading, spacing: SpacingRoles.Module.compact.rawValue) {
                    content()
                }
                .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
                .padding(.top, PaddingRoles.Screen.compact.rawValue)
                .padding(.bottom, PaddingRoles.Screen.regular.rawValue)
            }
            .scrollIndicators(.hidden)
        }
        .background(theme.colors.background.ignoresSafeArea())
    }
}
