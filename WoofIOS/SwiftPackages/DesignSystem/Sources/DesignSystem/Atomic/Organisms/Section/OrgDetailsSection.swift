import SwiftUI

public struct OrgDetailsRow: Hashable {
    public let label: String
    public let value: String

    public init(label: String, value: String) {
        self.label = label
        self.value = value
    }
}

public struct OrgDetailsSection: View {
    @Environment(\.theme) private var theme

    private let title: String
    private let rows: [OrgDetailsRow]
    private let toneRole: SectionToneRole

    public init(title: String, rows: [OrgDetailsRow], toneRole: SectionToneRole = .onScrim) {
        self.title = title
        self.rows = rows
        self.toneRole = toneRole
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            MolSectionTitle(title: title, toneRole: toneRole)
            ForEach(Array(rows.enumerated()), id: \.offset) { row in
                MolLabeledValue(label: row.element.label, value: row.element.value, toneRole: toneRole)
                AtomHorizontalDivider()
                    .opacity(row.offset < rows.count - 1 ? theme.alpha.enabled : 0)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(toneRole.containerPadding(from: theme))
        .background(toneRole.containerColor(from: theme), in: RoundedRectangle(cornerRadius: theme.sizing.radiusL))
    }
}
