import Resources
import SwiftUI

public struct AtomIcon: View {
    @Environment(\.theme) private var theme

    private let icon: ImageAsset
    private let iconSize: SizingRoles.Icon
    private let contentDescription: String?
    private let colorRole: IconColorRole

    public init(
        icon: ImageAsset,
        iconSize: SizingRoles.Icon,
        contentDescription: String? = nil,
        colorRole: IconColorRole = .onSurface
    ) {
        self.icon = icon
        self.iconSize = iconSize
        self.contentDescription = contentDescription
        self.colorRole = colorRole
    }

    public var body: some View {
        icon.image
            .resizable()
            .scaledToFit()
            .frame(width: iconSize.rawValue, height: iconSize.rawValue)
            .foregroundStyle(colorRole.color(from: theme))
            .accessibilityLabel(contentDescription ?? "")
    }
}
