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
        MolButtonContent(
            text: text,
            onTap: onTap,
            role: role,
            state: state,
            minHeight: theme.sizing.interactionHeightComfort,
            textFontRole: .displayH4,
            fillsWidth: true
        )
    }
}

public struct MolButtonCompact: View {
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
        MolButtonContent(
            text: text,
            onTap: onTap,
            role: role,
            state: state,
            minHeight: theme.sizing.interactionHeightDefault,
            textFontRole: .displayH5,
            fillsWidth: false
        )
    }
}

private struct MolButtonContent: View {
    @Environment(\.theme) private var theme

    let text: String
    let onTap: () -> Void
    let role: ButtonRole
    let state: ButtonState
    let minHeight: CGFloat
    let textFontRole: TextFontRole
    let fillsWidth: Bool

    var body: some View {
        Button(action: onTap) {
            ZStack {
                switch state.showsLoadingIndicator {
                case true:
                    AtomCircularProgressIndicator(iconSize: .m, colorRole: role.loadingColorRole)
                case false:
                    AtomText(
                        text: text,
                        textFontRole: textFontRole,
                        colorRole: role.textColorRole,
                        maxLines: 1,
                        textAlign: .center
                    )
                }
            }
            .padding(.horizontal, theme.padding.elementExpanded)
            .frame(maxWidth: fillsWidth ? .infinity : nil, minHeight: minHeight)
            .background(role.surfaceColor(from: theme), in: RoundedRectangle(cornerRadius: theme.sizing.radiusM))
        }
        .buttonStyle(.plain)
        .disabled(!state.isClickable)
        .opacity(state.alpha(from: theme))
    }
}
