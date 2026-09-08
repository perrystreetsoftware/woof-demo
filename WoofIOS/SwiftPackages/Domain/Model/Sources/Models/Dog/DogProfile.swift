import Foundation

public struct DogProfile: Hashable {
    public let dog: Dog
    public let breed: String
    public let ageInYears: Int
    public let size: DogSize
    public let neighborhood: String
    public let personality: [String]
    public let favoriteActivity: String
    public let bio: String

    public init(
        dog: Dog,
        breed: String,
        ageInYears: Int,
        size: DogSize,
        neighborhood: String,
        personality: [String],
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
