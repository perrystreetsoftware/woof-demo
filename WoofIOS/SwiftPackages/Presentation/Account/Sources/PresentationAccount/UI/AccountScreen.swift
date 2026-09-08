import DesignSystem
import PresentationCommon
import Resources
import SwiftUI

public struct AccountScreen: View {
    private let state: AccountViewModel.State

    public init(state: AccountViewModel.State) {
        self.state = state
    }

    public var body: some View {
        TemplateScrollableContent(topBar: { OrgNavigationHeaderTitle(title: L10n.Account.title) }) {
            switch state {
            case .loading:
                OrgSectionsPlaceholder()
            case .loaded(let account):
                AccountContent(account: account)
            }
        }
    }
}

private struct AccountContent: View {
    let account: AccountUIModel

    var body: some View {
        AsyncImageStateProvider(url: account.photoUrl) { imageState in
            OrgAvatarRow(
                title: account.name,
                subtitle: account.summary.text,
                imageState: imageState,
                contentDescription: L10n.Accessibility.dogPhoto(account.name)
            )
        }
        OrgTextSection(
            title: L10n.Profile.sectionAbout(account.name),
            text: account.bio,
            toneRole: .onSurface
        )
        OrgTagsSection(
            title: L10n.Profile.sectionPersonality,
            tags: account.personality,
            toneRole: .onSurface
        )
        OrgDetailsSection(
            title: L10n.Profile.sectionDetails,
            rows: account.rows.map { row in OrgDetailsRow(label: row.label, value: row.value) },
            toneRole: .onSurface
        )
    }
}

#Preview("Loaded") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        AccountScreen(state: AccountPreviewData.loaded())
    }
}

#Preview("Loading") {
    ThemedScreenPreview(theme: WoofTheme.dark()) {
        AccountScreen(state: .loading)
    }
}
