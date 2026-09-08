import Models
import Resources

extension ProfileDetailRowUIModel {
    var label: String {
        switch self {
        case .breed: L10n.Profile.labelBreed
        case .age: L10n.Profile.labelAge
        case .size: L10n.Profile.labelSize
        case .neighborhood: L10n.Profile.labelNeighborhood
        case .favoriteActivity: L10n.Profile.labelFavoriteActivity
        }
    }

    var value: String {
        switch self {
        case .breed(let breed): breed
        case .age(let ageInYears): L10n.Profile.age(ageInYears)
        case .size(let size): size.text
        case .neighborhood(let neighborhood): neighborhood
        case .favoriteActivity(let activity): activity
        }
    }
}

private extension DogSize {
    var text: String {
        switch self {
        case .small: L10n.Profile.sizeSmall
        case .medium: L10n.Profile.sizeMedium
        case .large: L10n.Profile.sizeLarge
        }
    }
}
