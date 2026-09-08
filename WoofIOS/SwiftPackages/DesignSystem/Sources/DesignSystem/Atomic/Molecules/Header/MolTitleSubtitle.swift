import SwiftUI

public struct MolTitleSubtitle: View {
    private let title: String
    private let subtitle: String

    public init(title: String, subtitle: String) {
        self.title = title
        self.subtitle = subtitle
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: SpacingRoles.Component.extraCompact.rawValue) {
            AtomText(text: title, textFontRole: .displayH1, colorRole: .onScrim, maxLines: 1)
            AtomText(text: subtitle, textFontRole: .subheadP2, colorRole: .onScrimVariant, maxLines: 2)
        }
    }
}
