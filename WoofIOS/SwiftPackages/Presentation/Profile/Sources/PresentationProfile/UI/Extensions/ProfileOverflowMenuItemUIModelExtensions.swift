import DesignSystem
import Resources

extension ProfileOverflowMenuItemUIModel {
    func toOverflowMenuItem(onTap: @escaping () -> Void) -> OrgOverflowMenuItem {
        switch self {
        case .report:
            OrgOverflowMenuItem(
                text: L10n.Profile.menuReport,
                icon: Asset.Icons.flag,
                onTap: onTap,
                isDestructive: true
            )
        case .block:
            OrgOverflowMenuItem(
                text: L10n.Profile.menuBlock,
                icon: Asset.Icons.block,
                onTap: onTap,
                isDestructive: true
            )
        }
    }
}
