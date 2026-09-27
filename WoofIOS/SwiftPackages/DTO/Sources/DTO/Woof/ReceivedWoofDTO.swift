import Foundation

public struct ReceivedWoofDTO: Codable, Equatable {
    public let dog: DogDTO
    public let woofedAtMillis: Int64
    public let woofedBack: Bool

    enum CodingKeys: String, CodingKey {
        case dog
        case woofedAtMillis = "woofed_at"
        case woofedBack = "woofed_back"
    }

    public init(dog: DogDTO, woofedAtMillis: Int64, woofedBack: Bool) {
        self.dog = dog
        self.woofedAtMillis = woofedAtMillis
        self.woofedBack = woofedBack
    }
}
