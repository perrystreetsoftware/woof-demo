import Resources

public enum IconButtonRole {
    case back
    case more
    case favorite
    case woof
    case send

    public var icon: ImageAsset {
        switch self {
        case .back: Asset.Icons.arrowBack
        case .more: Asset.Icons.moreVertical
        case .favorite: Asset.Icons.starOutline
        case .woof: Asset.Icons.pawOutline
        case .send: Asset.Icons.send
        }
    }

    public var activeIcon: ImageAsset {
        switch self {
        case .back: Asset.Icons.arrowBack
        case .more: Asset.Icons.moreVertical
        case .favorite: Asset.Icons.starFilled
        case .woof: Asset.Icons.pawFilled
        case .send: Asset.Icons.send
        }
    }

    public var contentDescription: String {
        switch self {
        case .back: L10n.Accessibility.back
        case .more: L10n.Accessibility.moreOptions
        case .favorite: L10n.Accessibility.addFavorite
        case .woof: L10n.Accessibility.woof
        case .send: L10n.Accessibility.sendMessage
        }
    }

    public var activeContentDescription: String {
        switch self {
        case .back: L10n.Accessibility.back
        case .more: L10n.Accessibility.moreOptions
        case .favorite: L10n.Accessibility.removeFavorite
        case .woof: L10n.Accessibility.woof
        case .send: L10n.Accessibility.sendMessage
        }
    }

    public var activeColorRole: IconColorRole {
        switch self {
        case .back: .primary
        case .more: .primary
        case .favorite: .recent
        case .woof: .primary
        case .send: .primary
        }
    }
}
