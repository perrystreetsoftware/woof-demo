import SwiftUI

public struct MolButton: View {
    @Environment(\.theme) private var theme

    private let text: String
    private let onTap: () -> Void
    private let role: ButtonRole
    private let state: ButtonState

    public init(text: String, onTap: @escaping () -> Void, role: ButtonRole = .primary, state: ButtonState = .enabled) {
        self.text = text
        self.onTap = onTap
        self.role = role
        self.state = state
    }

    public var body: some View {
        Button(action: onTap) {
            ZStack {
                switch state.showsLoadingIndicator {
                case true:
                    AtomCircularProgressIndicator(iconSize: .m, colorRole: role.loadingColorRole)
                case false:
                    AtomText(
                        text: text,
                        textFontRole: .displayH5,
                        colorRole: role.textColorRole,
                        maxLines: 1,
                        textAlign: .center
                    )
                }
            }
            .padding(.horizontal, PaddingRoles.Element.expanded.rawValue)
            .frame(minWidth: SizingRoles.InteractionHeight.default.rawValue, minHeight: SizingRoles.InteractionHeight.default.rawValue)
            .background(role.surfaceColor(from: theme), in: RoundedRectangle(cornerRadius: theme.radius.m))
        }
        .buttonStyle(.plain)
        .disabled(!state.isClickable)
        .opacity(state.alpha(from: theme))
    }
}
