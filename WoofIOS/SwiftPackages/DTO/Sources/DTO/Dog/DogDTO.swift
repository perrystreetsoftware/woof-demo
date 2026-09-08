import Foundation

public struct DogDTO: Codable, Equatable {
    public let id: Int
    public let name: String
    public let photoUrl: String

    enum CodingKeys: String, CodingKey {
        case id
        case name
        case photoUrl = "photo_url"
    }

    public init(id: Int, name: String, photoUrl: String) {
        self.id = id
        self.name = name
        self.photoUrl = photoUrl
    }
}
