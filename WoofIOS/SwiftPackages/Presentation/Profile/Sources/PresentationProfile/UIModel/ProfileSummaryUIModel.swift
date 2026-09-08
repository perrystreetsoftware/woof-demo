import Foundation

public struct ProfileSummaryUIModel: Hashable {
    public let ageInYears: Int
    public let breed: String
    public let neighborhood: String

    public init(ageInYears: Int, breed: String, neighborhood: String) {
        self.ageInYears = ageInYears
        self.breed = breed
        self.neighborhood = neighborhood
    }
}
