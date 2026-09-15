import Resources
import SwiftUI

public struct OrgOverflowMenuButton: View {
    private let items: [OrgOverflowMenuItem]
    private let isExpanded: Bool
    private let onExpandedChange: (Bool) -> Void
    private let colorRole: IconColorRole

    public init(
        items: [OrgOverflowMenuItem],
        isExpanded: Bool,
        onExpandedChange: @escaping (Bool) -> Void,
        colorRole: IconColorRole
    ) {
        self.items = items
        self.isExpanded = isExpanded
        self.onExpandedChange = onExpandedChange
        self.colorRole = colorRole
    }

    public var body: some View {
        Menu {
            ForEach(Array(items.enumerated()), id: \.offset) { item in
                MolDropdownMenuItem(
                    text: item.element.text,
                    icon: item.element.icon,
                    onTap: item.element.onTap,
                    isDestructive: item.element.isDestructive
                )
            }
        } label: {
            AtomIcon(
                icon: Asset.Icons.moreVertical,
                iconSize: .m,
                contentDescription: L10n.Accessibility.moreOptions,
                colorRole: colorRole
            )
            .frame(width: SizingRoles.InteractionHeight.default.rawValue, height: SizingRoles.InteractionHeight.default.rawValue)
        }
        .simultaneousGesture(TapGesture().onEnded { onExpandedChange(true) })
    }
}
