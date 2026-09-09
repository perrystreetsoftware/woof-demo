import SwiftUI

public struct AtomRoundedRectanglePlaceholder: View {
    @Environment(\.theme) private var theme

    public init() {}

    public var body: some View {
        RoundedRectangle(cornerRadius: theme.radius.s)
            .fill(theme.colors.placeholder)
    }
}
