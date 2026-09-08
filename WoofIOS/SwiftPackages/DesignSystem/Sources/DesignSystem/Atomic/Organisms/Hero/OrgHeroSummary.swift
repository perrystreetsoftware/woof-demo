import SwiftUI

public struct OrgHeroSummary: View {
    private let title: String
    private let subtitle: String?
    private let tags: [String]

    public init(title: String, subtitle: String?, tags: [String]) {
        self.title = title
        self.subtitle = subtitle
        self.tags = tags
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: SpacingRoles.Component.cozy.rawValue) {
            switch subtitle {
            case .none:
                OrgHeroSummaryTitleLoading(title: title)
                MolTagGroupPlaceholder()
            case .some(let subtitle):
                MolTitleSubtitle(title: title, subtitle: subtitle)
                MolTagGroup(tags: tags)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}

private struct OrgHeroSummaryTitleLoading: View {
    let title: String

    var body: some View {
        VStack(alignment: .leading, spacing: SpacingRoles.Component.extraCompact.rawValue) {
            AtomText(text: title, textFontRole: .displayH1, colorRole: .onScrim, maxLines: 1)
            AtomTextPlaceholder(role: .subtitle)
                .atomPlaceholderShimmer()
        }
    }
}
