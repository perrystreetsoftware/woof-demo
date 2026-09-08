import Resources
import SwiftUI

public struct MolIconButton: View {
    @Environment(\.theme) private var theme

    private let role: IconButtonRole
    private let onTap: () -> Void
    private let isActive: Bool
    private let isOnScrim: Bool
    private let hasBackground: Bool

    private static let pressedScale: CGFloat = 0.85

    public init(
        role: IconButtonRole,
        onTap: @escaping () -> Void,
        isActive: Bool = false,
        isOnScrim: Bool = false,
        hasBackground: Bool = false
    ) {
        self.role = role
        self.onTap = onTap
        self.isActive = isActive
        self.isOnScrim = isOnScrim
        self.hasBackground = hasBackground
    }

    public var body: some View {
        Button(action: onTap) {
            AtomIcon(
                icon: isActive ? role.activeIcon : role.icon,
                iconSize: .m,
                contentDescription: isActive ? role.activeContentDescription : role.contentDescription,
                colorRole: colorRole
            )
            .frame(width: theme.sizing.interactionHeightDefault, height: theme.sizing.interactionHeightDefault)
            .background(backgroundColor, in: Circle())
        }
        .buttonStyle(MolIconButtonStyle(pressedScale: Self.pressedScale, pressedAlpha: theme.alpha.pressed, enabledAlpha: theme.alpha.enabled))
    }

    private var colorRole: IconColorRole {
        switch (isActive, isOnScrim) {
        case (true, _): role.activeColorRole
        case (false, true): .onScrim
        case (false, false): .onSurface
        }
    }

    private var backgroundColor: Color {
        switch hasBackground {
        case true: theme.colors.scrimContainer
        case false: Color.clear
        }
    }
}

private struct MolIconButtonStyle: ButtonStyle {
    let pressedScale: CGFloat
    let pressedAlpha: Double
    let enabledAlpha: Double

    func makeBody(configuration: Configuration) -> some View {
        configuration.label
            .scaleEffect(configuration.isPressed ? pressedScale : 1)
            .opacity(configuration.isPressed ? pressedAlpha : enabledAlpha)
            .animation(.default, value: configuration.isPressed)
    }
}
