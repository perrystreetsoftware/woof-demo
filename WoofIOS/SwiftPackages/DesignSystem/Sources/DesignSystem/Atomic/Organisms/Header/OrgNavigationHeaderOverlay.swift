import Resources
import SwiftUI

public struct OrgNavigationHeaderOverlay: View {
    private let onBackTap: () -> Void
    private let actions: [OrgNavigationHeaderActionItem]
    private let overflowItems: [OrgOverflowMenuItem]
    private let isOverflowExpanded: Bool
    private let onOverflowExpandedChange: (Bool) -> Void

    public init(
        onBackTap: @escaping () -> Void,
        actions: [OrgNavigationHeaderActionItem] = [],
        overflowItems: [OrgOverflowMenuItem] = [],
        isOverflowExpanded: Bool = false,
        onOverflowExpandedChange: @escaping (Bool) -> Void = { _ in }
    ) {
        self.onBackTap = onBackTap
        self.actions = actions
        self.overflowItems = overflowItems
        self.isOverflowExpanded = isOverflowExpanded
        self.onOverflowExpandedChange = onOverflowExpandedChange
    }

    public var body: some View {
        HStack(spacing: 0) {
            AtomIconButton(
                icon: Asset.Icons.arrowBack,
                contentDescription: L10n.Accessibility.back,
                colorRole: .onScrim,
                backgroundRole: .none,
                onTap: onBackTap
            )
            Spacer(minLength: 0)
            ForEach(Array(actions.enumerated()), id: \.offset) { action in
                AtomIconButton(
                    icon: action.element.icon,
                    contentDescription: action.element.contentDescription,
                    colorRole: action.element.colorRole,
                    backgroundRole: .none,
                    onTap: action.element.onTap
                )
            }
            OrgOverflowMenuButton(
                items: overflowItems,
                isExpanded: isOverflowExpanded,
                onExpandedChange: onOverflowExpandedChange,
                colorRole: .onScrim
            )
        }
        .padding(.horizontal, PaddingRoles.Element.compact.rawValue)
        .frame(maxWidth: .infinity, minHeight: SizingRoles.InteractionHeight.comfort.rawValue)
    }
}
