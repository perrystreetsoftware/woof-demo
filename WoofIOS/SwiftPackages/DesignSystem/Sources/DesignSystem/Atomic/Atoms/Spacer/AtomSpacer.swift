import SwiftUI

public struct AtomSpacer: View {
    private let spacing: SpacingRoles.Component

    public init(spacing: SpacingRoles.Component) {
        self.spacing = spacing
    }

    public var body: some View {
        Color.clear
            .frame(width: spacing.rawValue, height: spacing.rawValue)
    }
}
