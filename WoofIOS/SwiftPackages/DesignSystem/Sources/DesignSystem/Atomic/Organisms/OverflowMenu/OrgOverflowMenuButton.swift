import SwiftUI

public struct OrgOverflowMenuButton: View {
    private let items: [OrgOverflowMenuItem]
    private let isExpanded: Bool
    private let onExpandedChange: (Bool) -> Void
    private let isOnScrim: Bool

    public init(
        items: [OrgOverflowMenuItem],
        isExpanded: Bool,
        onExpandedChange: @escaping (Bool) -> Void,
        isOnScrim: Bool = false
    ) {
        self.items = items
        self.isExpanded = isExpanded
        self.onExpandedChange = onExpandedChange
        self.isOnScrim = isOnScrim
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
                icon: IconButtonRole.more.icon,
                iconSize: .m,
                contentDescription: IconButtonRole.more.contentDescription,
                colorRole: colorRole
            )
            .frame(width: SizingRoles.InteractionHeight.default.rawValue, height: SizingRoles.InteractionHeight.default.rawValue)
        }
        .simultaneousGesture(TapGesture().onEnded { onExpandedChange(true) })
    }

    private var colorRole: IconColorRole {
        switch (isExpanded, isOnScrim) {
        case (true, _): IconButtonRole.more.activeColorRole
        case (false, true): .onScrim
        case (false, false): .onSurface
        }
    }
}
