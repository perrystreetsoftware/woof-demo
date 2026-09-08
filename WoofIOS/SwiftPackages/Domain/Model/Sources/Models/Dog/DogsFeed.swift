import Foundation

public struct DogsFeed: Hashable {
    public let dogs: [Dog]
    public let total: Int

    public var hasMore: Bool {
        dogs.count < total
    }

    public static let empty = DogsFeed(dogs: [], total: 0)

    public init(dogs: [Dog], total: Int) {
        self.dogs = dogs
        self.total = total
    }
}
