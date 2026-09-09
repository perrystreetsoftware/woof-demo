import SwiftUI

public struct TemplateCenteredContent<TopBar: View, Content: View>: View {
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
            content()
                .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
                .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
        .background(theme.colors.background.ignoresSafeArea())
    }
}
