import DesignSystem
import Resources
import SwiftUI

struct ProfileModerationDialog: ViewModifier {
    let dialog: ProfileModerationDialogUIModel?
    let onConfirmTap: () -> Void
    let onDismissTap: () -> Void

    func body(content: Content) -> some View {
        content
            .orgAlertDialog(
                isPresented: dialog != nil,
                title: dialog?.title ?? "",
                message: dialog?.message ?? "",
                confirmText: dialog?.confirmText ?? "",
                dismissText: L10n.Profile.dialogCancel,
                onConfirmTap: onConfirmTap,
                onDismissTap: onDismissTap,
                isDestructive: true
            )
    }
}

extension View {
    func profileModerationDialog(
        dialog: ProfileModerationDialogUIModel?,
        onConfirmTap: @escaping () -> Void,
        onDismissTap: @escaping () -> Void
    ) -> some View {
        modifier(ProfileModerationDialog(dialog: dialog, onConfirmTap: onConfirmTap, onDismissTap: onDismissTap))
    }
}
