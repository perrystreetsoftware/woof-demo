import SwiftUI

public struct AtomCircularProgressIndicator: View {
    @Environment(\.theme) private var theme

    private let iconSize: SizingRoles.Icon
    private let colorRole: IconColorRole

    public init(iconSize: SizingRoles.Icon = .m, colorRole: IconColorRole = .primary) {
        self.iconSize = iconSize
        self.colorRole = colorRole
    }

    public var body: some View {
        ProgressView()
            .tint(colorRole.color(from: theme))
            .frame(width: iconSize.rawValue, height: iconSize.rawValue)
    }
}
