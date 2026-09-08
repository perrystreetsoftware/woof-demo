import SwiftUI

public struct OrgTagsSection: View {
    @Environment(\.theme) private var theme

    private let title: String
    private let tags: [String]
    private let toneRole: SectionToneRole

    public init(title: String, tags: [String], toneRole: SectionToneRole = .onScrim) {
        self.title = title
        self.tags = tags
        self.toneRole = toneRole
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: SpacingRoles.Component.cozy.rawValue) {
            MolSectionTitle(title: title, toneRole: toneRole)
            MolTagGroup(tags: tags, styleRole: toneRole.tagStyleRole)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(toneRole.containerPadding(from: theme))
        .background(toneRole.containerColor(from: theme), in: RoundedRectangle(cornerRadius: theme.sizing.radiusL))
    }
}
