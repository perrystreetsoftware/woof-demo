import Foundation

public enum WoofDestination: Hashable {
    case home
    case profile(dogId: Int)
}
