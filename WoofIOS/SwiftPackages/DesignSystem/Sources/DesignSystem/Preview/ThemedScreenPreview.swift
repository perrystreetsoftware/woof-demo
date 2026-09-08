import SwiftUI

public struct ThemedScreenPreview<Content: View>: View {
    private let theme: ThemeImplementing
    private let content: () -> Content

    public init(theme: ThemeImplementing, @ViewBuilder content: @escaping () -> Content) {
        self.theme = theme
        self.content = content
    }

    public var body: some View {
        content()
            .theme(theme)
    }
}
