import SwiftUI

public struct OrgEmptyState: View {
    @Environment(\.theme) private var theme

    private let title: String
    private let message: String

    public init(title: String, message: String) {
        self.title = title
        self.message = message
    }

    public var body: some View {
        VStack(spacing: 0) {
            AtomAppLogo(colorRole: .onSurfaceVariant)
            AtomSpacer(spacing: .regular)
            AtomText(text: title, textFontRole: .displayH2, textAlign: .center)
            AtomSpacer(spacing: .compact)
            AtomText(text: message, textFontRole: .bodyP1, colorRole: .onSurfaceVariant, textAlign: .center)
        }
        .padding(.horizontal, theme.padding.screenHorizontal)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}
