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
            .padding(.horizontal, PaddingRoles.Element.relaxed.rawValue)
            .padding(.vertical, PaddingRoles.Element.compact.rawValue)
            .background(theme.colors.placeholder, in: RoundedRectangle(cornerRadius: theme.radius.xl))
            .accessibilityHidden(true)
    }
}
