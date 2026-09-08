import DesignSystem
import Resources
import SwiftUI

struct ProfileSection: View {
    let section: ProfileSectionUIModel

    var body: some View {
        switch section {
        case .about(let name, let bio):
            OrgTextSection(
                title: L10n.Profile.sectionAbout(name),
                text: bio
            )
        case .personality(let tags):
            OrgTagsSection(
                title: L10n.Profile.sectionPersonality,
                tags: tags
            )
        case .details(let rows):
            OrgDetailsSection(
                title: L10n.Profile.sectionDetails,
                rows: rows.map { row in OrgDetailsRow(label: row.label, value: row.value) }
            )
        }
    }
}
