import Foundation

public struct DogProfileDTO: Codable, Equatable {
    public let dog: DogDTO
    public let breed: String
    public let ageInYears: Int
    public let size: String
    public let neighborhood: String
    public let personality: [String]
    public let favoriteActivity: String
    public let bio: String

    enum CodingKeys: String, CodingKey {
        case dog
        case breed
        case ageInYears = "age_in_years"
        case size
        case neighborhood
        case personality
        case favoriteActivity = "favorite_activity"
        case bio
    }

    public init(
        dog: DogDTO,
        breed: String,
        ageInYears: Int,
        size: String,
        neighborhood: String,
        personality: [String] = [],
        favoriteActivity: String,
        bio: String
    ) {
        self.dog = dog
        self.breed = breed
        self.ageInYears = ageInYears
        self.size = size
        self.neighborhood = neighborhood
        self.personality = personality
        self.favoriteActivity = favoriteActivity
        self.bio = bio
    }
}
