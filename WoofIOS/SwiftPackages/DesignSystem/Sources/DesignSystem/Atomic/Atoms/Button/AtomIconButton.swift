import Resources
import SwiftUI

public struct AtomIconButton: View {
    @Environment(\.theme) private var theme

    private let icon: ImageAsset
    private let contentDescription: String
    private let colorRole: IconColorRole
    private let backgroundRole: ButtonBackgroundRole
    private let onTap: () -> Void

    private static let pressedScale: CGFloat = 0.85

    public init(
        icon: ImageAsset,
        contentDescription: String,
        colorRole: IconColorRole,
        backgroundRole: ButtonBackgroundRole,
        onTap: @escaping () -> Void
    ) {
        self.icon = icon
        self.contentDescription = contentDescription
        self.colorRole = colorRole
        self.backgroundRole = backgroundRole
        self.onTap = onTap
    }

    public var body: some View {
        Button(action: onTap) {
            icon.image
                .resizable()
                .scaledToFit()
                .frame(width: SizingRoles.Icon.m.rawValue, height: SizingRoles.Icon.m.rawValue)
                .foregroundStyle(colorRole.color(from: theme))
                .accessibilityLabel(contentDescription)
                .frame(width: SizingRoles.InteractionHeight.default.rawValue, height: SizingRoles.InteractionHeight.default.rawValue)
                .background(backgroundRole.color(from: theme), in: Circle())
        }
        .buttonStyle(IconButtonPressStyle(pressedScale: Self.pressedScale, pressedAlpha: theme.alpha.pressed, enabledAlpha: theme.alpha.enabled))
    }
}

private struct IconButtonPressStyle: ButtonStyle {
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
