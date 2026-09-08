import SwiftUI

public struct OrgTextSection: View {
    @Environment(\.theme) private var theme

    private let title: String
    private let text: String
    private let toneRole: SectionToneRole

    public init(title: String, text: String, toneRole: SectionToneRole = .onScrim) {
        self.title = title
        self.text = text
        self.toneRole = toneRole
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: SpacingRoles.Component.cozy.rawValue) {
            MolSectionTitle(title: title, toneRole: toneRole)
            AtomText(text: text, textFontRole: .bodyP1, colorRole: toneRole.bodyColorRole)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(toneRole.containerPadding(from: theme))
        .background(toneRole.containerColor(from: theme), in: RoundedRectangle(cornerRadius: theme.sizing.radiusL))
    }
}
