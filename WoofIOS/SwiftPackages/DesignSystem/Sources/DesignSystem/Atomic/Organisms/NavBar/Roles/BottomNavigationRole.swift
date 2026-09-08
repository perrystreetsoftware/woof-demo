import Resources

public enum BottomNavigationRole {
    case browse
    case favorites
    case account

    public var icon: ImageAsset {
        switch self {
        case .browse: Asset.Icons.browseOutline
        case .favorites: Asset.Icons.starOutline
        case .account: Asset.Icons.accountOutline
        }
    }

    public var selectedIcon: ImageAsset {
        switch self {
        case .browse: Asset.Icons.browseFilled
        case .favorites: Asset.Icons.starFilled
        case .account: Asset.Icons.accountFilled
        }
    }

    public var label: String {
        switch self {
        case .browse: L10n.Home.Tab.browse
        case .favorites: L10n.Home.Tab.favorites
        case .account: L10n.Home.Tab.account
        }
    }
}
