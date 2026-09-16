import Resources

extension HomeTabUIModel {
    func icon(isSelected: Bool) -> ImageAsset {
        switch (self, isSelected) {
        case (.browse, true): Asset.Icons.browseFilled
        case (.browse, false): Asset.Icons.browseOutline
        case (.favorites, true): Asset.Icons.starFilled
        case (.favorites, false): Asset.Icons.starOutline
        case (.account, true): Asset.Icons.accountFilled
        case (.account, false): Asset.Icons.accountOutline
        }
    }

    var label: String {
        switch self {
        case .browse: L10n.Home.Tab.browse
        case .favorites: L10n.Home.Tab.favorites
        case .account: L10n.Home.Tab.account
        }
    }
}
