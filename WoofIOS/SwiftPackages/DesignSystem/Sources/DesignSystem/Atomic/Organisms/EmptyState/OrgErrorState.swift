import SwiftUI

public struct OrgErrorState: View {
    @Environment(\.theme) private var theme

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
            AtomAppLogo(colorRole: .onSurfaceVariant)
            AtomSpacer(spacing: .regular)
            AtomText(text: title, textFontRole: .displayH2, textAlign: .center)
            AtomSpacer(spacing: .compact)
            AtomText(text: message, textFontRole: .bodyP1, colorRole: .onSurfaceVariant, textAlign: .center)
            AtomSpacer(spacing: .expanded)
            MolButtonCompact(text: actionText, onTap: onActionTap)
        }
        .padding(.horizontal, theme.padding.screenHorizontal)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
