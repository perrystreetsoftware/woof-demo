import SwiftUI

public struct OrgTypeBarWithAction: View {
    @Environment(\.theme) private var theme

    private let text: String
    private let placeholder: String
    private let onTextChange: (String) -> Void
    private let onSubmit: () -> Void
    private let actionRole: IconButtonRole
    private let isActionActive: Bool
    private let onActionTap: () -> Void
    private let isSubmitEnabled: Bool

    public init(
        text: String,
        placeholder: String,
        onTextChange: @escaping (String) -> Void,
        onSubmit: @escaping () -> Void,
        actionRole: IconButtonRole,
        isActionActive: Bool,
        onActionTap: @escaping () -> Void,
        isSubmitEnabled: Bool
    ) {
        self.text = text
        self.placeholder = placeholder
        self.onTextChange = onTextChange
        self.onSubmit = onSubmit
        self.actionRole = actionRole
        self.isActionActive = isActionActive
        self.onActionTap = onActionTap
        self.isSubmitEnabled = isSubmitEnabled
    }

    public var body: some View {
        HStack(spacing: SpacingRoles.Component.compact.rawValue) {
            MolTypeBar(
                text: text,
                placeholder: placeholder,
                onTextChange: onTextChange,
                onSubmit: onSubmit,
                isSubmitEnabled: isSubmitEnabled
            )
            MolIconButton(
                role: actionRole,
                onTap: onActionTap,
                isActive: isActionActive,
                isOnScrim: true,
                hasBackground: true
            )
        }
        .padding(.horizontal, theme.padding.elementRelaxed)
        .padding(.vertical, theme.padding.elementRegular)
    }
}
