import Resources
import SwiftUI

public struct MolTypeBar: View {
    @Environment(\.theme) private var theme

    private let text: String
    private let placeholder: String
    private let onTextChange: (String) -> Void
    private let onSubmit: () -> Void
    private let isSubmitEnabled: Bool

    public init(
        text: String,
        placeholder: String,
        onTextChange: @escaping (String) -> Void,
        onSubmit: @escaping () -> Void,
        isSubmitEnabled: Bool
    ) {
        self.text = text
        self.placeholder = placeholder
        self.onTextChange = onTextChange
        self.onSubmit = onSubmit
        self.isSubmitEnabled = isSubmitEnabled
    }

    public var body: some View {
        HStack(spacing: SpacingRoles.Component.compact.rawValue) {
            TextField(
                "",
                text: Binding(get: { text }, set: onTextChange),
                prompt: Text(placeholder).foregroundStyle(theme.colors.onScrimVariant)
            )
            .font(theme.typography.body.p1)
            .foregroundStyle(theme.colors.onScrim)
            .tint(theme.colors.primary)
            .submitLabel(.send)
            .onSubmit(onSubmit)
            Button(action: onSubmit) {
                AtomIcon(
                    icon: Asset.Icons.send,
                    iconSize: .m,
                    contentDescription: L10n.Accessibility.sendMessage,
                    colorRole: submitColorRole
                )
            }
            .buttonStyle(.plain)
            .disabled(!isSubmitEnabled)
        }
        .padding(.leading, theme.padding.elementExpanded)
        .padding(.trailing, theme.padding.elementRelaxed)
        .padding(.vertical, theme.padding.elementRelaxed)
        .frame(minHeight: theme.sizing.interactionHeightDefault)
        .background(theme.colors.scrimContainer, in: RoundedRectangle(cornerRadius: theme.sizing.radiusXL))
    }

    private var submitColorRole: IconColorRole {
        switch isSubmitEnabled {
        case true: .primary
        case false: .onScrimVariant
        }
    }
}
