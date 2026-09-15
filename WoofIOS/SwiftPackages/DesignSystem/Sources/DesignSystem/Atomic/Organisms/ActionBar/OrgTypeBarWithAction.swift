import Resources
import SwiftUI

public struct OrgTypeBarWithAction: View {
    private let text: String
    private let placeholder: String
    private let onTextChange: (String) -> Void
    private let onSubmit: () -> Void
    private let actionIcon: ImageAsset
    private let actionContentDescription: String
    private let actionColorRole: IconColorRole
    private let onActionTap: () -> Void
    private let isSubmitEnabled: Bool

    public init(
        text: String,
        placeholder: String,
        onTextChange: @escaping (String) -> Void,
        onSubmit: @escaping () -> Void,
        actionIcon: ImageAsset,
        actionContentDescription: String,
        actionColorRole: IconColorRole,
        onActionTap: @escaping () -> Void,
        isSubmitEnabled: Bool
    ) {
        self.text = text
        self.placeholder = placeholder
        self.onTextChange = onTextChange
        self.onSubmit = onSubmit
        self.actionIcon = actionIcon
        self.actionContentDescription = actionContentDescription
        self.actionColorRole = actionColorRole
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
            AtomIconButton(
                icon: actionIcon,
                contentDescription: actionContentDescription,
                colorRole: actionColorRole,
                backgroundRole: .scrimContainer,
                onTap: onActionTap
            )
        }
        .padding(.horizontal, PaddingRoles.Element.relaxed.rawValue)
        .padding(.vertical, PaddingRoles.Element.regular.rawValue)
    }
}
