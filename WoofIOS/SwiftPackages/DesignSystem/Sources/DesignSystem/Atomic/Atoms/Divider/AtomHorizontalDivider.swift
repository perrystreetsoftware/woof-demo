import SwiftUI

public struct AtomHorizontalDivider: View {
    @Environment(\.theme) private var theme

    public init() {}

    public var body: some View {
        Rectangle()
            .fill(theme.colors.outlineVariant)
            .frame(height: SizingRoles.HorizontalRule.xs.rawValue)
    }
}
