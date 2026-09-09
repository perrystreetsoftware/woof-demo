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
            MolIconButton(role: .back, onTap: onBackTap, isOnScrim: true)
            Spacer(minLength: 0)
            ForEach(Array(actions.enumerated()), id: \.offset) { action in
                MolIconButton(
                    role: action.element.role,
                    onTap: action.element.onTap,
                    isActive: action.element.isActive,
                    isOnScrim: true
                )
            }
            OrgOverflowMenuButton(
                items: overflowItems,
                isExpanded: isOverflowExpanded,
                onExpandedChange: onOverflowExpandedChange,
                isOnScrim: true
            )
        }
        .padding(.horizontal, PaddingRoles.Element.compact.rawValue)
        .frame(maxWidth: .infinity, minHeight: SizingRoles.InteractionHeight.comfort.rawValue)
    }
}
