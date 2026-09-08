import SwiftUI

public struct MolSectionTitle: View {
    private let title: String
    private let toneRole: SectionToneRole

    public init(title: String, toneRole: SectionToneRole = .onScrim) {
        self.title = title
        self.toneRole = toneRole
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: SpacingRoles.Component.compact.rawValue) {
            AtomText(text: title, textFontRole: .displayH4, colorRole: toneRole.titleColorRole, maxLines: 1)
            AtomHorizontalDivider()
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}
