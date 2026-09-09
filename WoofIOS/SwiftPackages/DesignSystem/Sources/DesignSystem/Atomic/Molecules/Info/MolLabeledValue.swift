import SwiftUI

public struct MolLabeledValue: View {
    private let label: String
    private let value: String
    private let toneRole: SectionToneRole

    public init(label: String, value: String, toneRole: SectionToneRole = .onScrim) {
        self.label = label
        self.value = value
        self.toneRole = toneRole
    }

    public var body: some View {
        HStack(spacing: SpacingRoles.Component.regular.rawValue) {
            AtomText(text: label, textFontRole: .bodyP2, colorRole: toneRole.labelColorRole, maxLines: 1)
            AtomText(text: value, textFontRole: .subheadP2, colorRole: toneRole.bodyColorRole, maxLines: 2, textAlign: .trailing)
                .frame(maxWidth: .infinity, alignment: .trailing)
        }
        .padding(.vertical, PaddingRoles.Element.relaxed.rawValue)
    }
}
