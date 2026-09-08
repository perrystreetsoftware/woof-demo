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

public struct ThemeModifier: ViewModifier {
    private let theme: ThemeImplementing

    public init(theme: ThemeImplementing) {
        self.theme = theme
    }

    public func body(content: Content) -> some View {
        content
            .tint(theme.colors.primary)
            .foregroundStyle(theme.colors.onSurface)
            .font(theme.typography.body.p1)
            .preferredColorScheme(theme.isDark ? .dark : .light)
            .environment(\.theme, theme)
    }
}

public extension View {
    func theme(_ theme: ThemeImplementing) -> some View {
        modifier(ThemeModifier(theme: theme))
    }
}
