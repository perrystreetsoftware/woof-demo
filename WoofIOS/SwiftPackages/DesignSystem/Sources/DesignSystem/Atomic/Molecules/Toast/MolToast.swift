import Resources
import SwiftUI

public struct MolToast: View {
    @Environment(\.theme) private var theme

    private let text: String

    public init(text: String) {
        self.text = text
    }

    public var body: some View {
        HStack(spacing: SpacingRoles.Component.cozy.rawValue) {
            AtomIcon(icon: Asset.Icons.check, iconSize: .s, colorRole: .primary)
            AtomText(text: text, textFontRole: .subheadP2, maxLines: 2)
        }
        .padding(.horizontal, PaddingRoles.Element.expanded.rawValue)
        .padding(.vertical, PaddingRoles.Element.relaxed.rawValue)
        .background(theme.colors.surfaceContainer, in: RoundedRectangle(cornerRadius: theme.radius.l))
        .shadow(color: theme.colors.shadow.opacity(theme.alpha.shimmerLowMedium), radius: theme.radius.s)
    }
}
