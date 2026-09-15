import DesignSystem
import Resources

extension ProfileHeaderViewModel.State {
    var favoriteIcon: ImageAsset {
        switch isFavorite {
        case true: Asset.Icons.starFilled
        case false: Asset.Icons.starOutline
        }
    }

    var favoriteContentDescription: String {
        switch isFavorite {
        case true: L10n.Accessibility.removeFavorite
        case false: L10n.Accessibility.addFavorite
        }
    }

    var favoriteColorRole: IconColorRole {
        switch isFavorite {
        case true: .recent
        case false: .onScrim
        }
    }
}
