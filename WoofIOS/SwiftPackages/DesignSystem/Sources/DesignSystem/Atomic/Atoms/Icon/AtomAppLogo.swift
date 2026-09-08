import Resources
import SwiftUI

public struct AtomAppLogo: View {
    @Environment(\.theme) private var theme

    private let colorRole: IconColorRole

    public init(colorRole: IconColorRole = .primary) {
        self.colorRole = colorRole
    }

    public var body: some View {
        Asset.Icons.logo.image
            .resizable()
            .scaledToFit()
            .frame(width: SizingRoles.Icon.l.rawValue, height: SizingRoles.Icon.l.rawValue)
            .foregroundStyle(colorRole.color(from: theme))
            .accessibilityLabel(L10n.Accessibility.appLogo)
    }
}
