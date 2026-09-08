import Foundation

public struct DogsPageDTO: Codable, Equatable {
    public let results: [DogDTO]
    public let offset: Int
    public let total: Int

    enum CodingKeys: String, CodingKey {
        case results
        case offset
        case total
    }

    public init(results: [DogDTO] = [], offset: Int, total: Int) {
        self.results = results
        self.offset = offset
        self.total = total
    }
}
