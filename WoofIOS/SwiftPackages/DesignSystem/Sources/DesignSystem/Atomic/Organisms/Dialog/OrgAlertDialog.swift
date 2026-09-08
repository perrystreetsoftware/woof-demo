import SwiftUI

public struct OrgAlertDialog: ViewModifier {
    private let isPresented: Bool
    private let title: String
    private let message: String
    private let confirmText: String
    private let dismissText: String
    private let onConfirmTap: () -> Void
    private let onDismissTap: () -> Void
    private let isDestructive: Bool

    public init(
        isPresented: Bool,
        title: String,
        message: String,
        confirmText: String,
        dismissText: String,
        onConfirmTap: @escaping () -> Void,
        onDismissTap: @escaping () -> Void,
        isDestructive: Bool = false
    ) {
        self.isPresented = isPresented
        self.title = title
        self.message = message
        self.confirmText = confirmText
        self.dismissText = dismissText
        self.onConfirmTap = onConfirmTap
        self.onDismissTap = onDismissTap
        self.isDestructive = isDestructive
    }

    public func body(content: Content) -> some View {
        content
            .alert(title, isPresented: Binding(get: { isPresented }, set: { _ in })) {
                Button(dismissText, role: .cancel, action: onDismissTap)
                Button(confirmText, role: isDestructive ? .destructive : nil, action: onConfirmTap)
            } message: {
                Text(message)
            }
    }
}

public extension View {
    func orgAlertDialog(
        isPresented: Bool,
        title: String,
        message: String,
        confirmText: String,
        dismissText: String,
        onConfirmTap: @escaping () -> Void,
        onDismissTap: @escaping () -> Void,
        isDestructive: Bool = false
    ) -> some View {
        modifier(
            OrgAlertDialog(
                isPresented: isPresented,
                title: title,
                message: message,
                confirmText: confirmText,
                dismissText: dismissText,
                onConfirmTap: onConfirmTap,
                onDismissTap: onDismissTap,
                isDestructive: isDestructive
            )
        )
    }
}
