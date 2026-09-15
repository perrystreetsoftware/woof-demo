import SwiftUI

public struct ThemeKey: EnvironmentKey {
    public static let defaultValue: ThemeImplementing = WoofTheme.light()
}

public extension EnvironmentValues {
    var theme: ThemeImplementing {
        get { self[ThemeKey.self] }
        set { self[ThemeKey.self] = newValue }
    }
}

public struct Theme<Content: View>: View {
    private let theme: ThemeImplementing
    private let content: () -> Content

    public init(theme: ThemeImplementing, @ViewBuilder content: @escaping () -> Content) {
        self.theme = theme
        self.content = content
    }

    public var body: some View {
        content()
            .tint(theme.colors.primary)
            .foregroundStyle(theme.colors.onSurface)
            .font(theme.typography.body.p1)
            .preferredColorScheme(theme.isDark ? .dark : .light)
            .environment(\.theme, theme)
    }
}
