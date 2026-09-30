import SwiftUI

public struct OrgErrorState: View {
    private let title: String
    private let message: String
    private let actionText: String
    private let onActionTap: () -> Void

    public init(title: String, message: String, actionText: String, onActionTap: @escaping () -> Void) {
        self.title = title
        self.message = message
        self.actionText = actionText
        self.onActionTap = onActionTap
    }

    public var body: some View {
        VStack(spacing: 0) {
            OrgEmptyState(title: title, message: message)
            AtomSpacer(spacing: .expanded)
            MolButton(text: actionText, onTap: onActionTap)
        }
    }
}
