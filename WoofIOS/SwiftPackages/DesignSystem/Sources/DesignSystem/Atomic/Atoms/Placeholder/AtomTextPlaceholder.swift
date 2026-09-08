import SwiftUI

public struct AtomTextPlaceholder: View {
    @Environment(\.theme) private var theme

    private let role: TextPlaceholderRole

    public init(role: TextPlaceholderRole) {
        self.role = role
    }

    public var body: some View {
        Text(role.sampleText)
            .font(role.font(from: theme))
            .lineLimit(1)
            .opacity(0)
            .background(theme.colors.placeholder, in: RoundedRectangle(cornerRadius: theme.sizing.radiusS))
            .accessibilityHidden(true)
    }
}
