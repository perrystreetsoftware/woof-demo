import Resources
import SwiftUI

public struct OrgOverflowMenuButton: View {
    private let items: [OrgOverflowMenuItem]
    private let state: OverflowMenuState
    private let onExpandedChange: (Bool) -> Void

    public init(
        items: [OrgOverflowMenuItem],
        state: OverflowMenuState,
        onExpandedChange: @escaping (Bool) -> Void
    ) {
        self.items = items
        self.state = state
        self.onExpandedChange = onExpandedChange
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
                colorRole: state.colorRole
            )
            .frame(width: SizingRoles.InteractionHeight.default.rawValue, height: SizingRoles.InteractionHeight.default.rawValue)
        }
        .simultaneousGesture(TapGesture().onEnded { onExpandedChange(true) })
    }
}
