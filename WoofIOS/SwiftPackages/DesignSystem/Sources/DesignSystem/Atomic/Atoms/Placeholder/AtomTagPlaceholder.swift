import SwiftUI

public struct AtomTagPlaceholder: View {
    @Environment(\.theme) private var theme

    private let text: String

    public init(text: String) {
        self.text = text
    }

    public var body: some View {
        Text(text)
            .font(theme.typography.subhead.p3)
            .lineLimit(1)
            .opacity(0)
            .padding(.horizontal, theme.padding.elementRelaxed)
            .padding(.vertical, theme.padding.elementCompact)
            .background(theme.colors.placeholder, in: RoundedRectangle(cornerRadius: theme.sizing.radiusXL))
            .accessibilityHidden(true)
    }
}
