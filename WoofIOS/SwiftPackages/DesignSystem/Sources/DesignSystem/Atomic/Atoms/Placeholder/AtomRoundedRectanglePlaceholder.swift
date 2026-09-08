import SwiftUI

public struct AtomRoundedRectanglePlaceholder: View {
    @Environment(\.theme) private var theme

    public init() {}

    public var body: some View {
        RoundedRectangle(cornerRadius: theme.sizing.radiusS)
            .fill(theme.colors.placeholder)
    }
}
