import Foundation

public struct DogsPage: Hashable {
    public let dogs: [Dog]
    public let offset: Int
    public let total: Int

    public init(dogs: [Dog], offset: Int, total: Int) {
        self.dogs = dogs
        self.offset = offset
        self.total = total
    }
}
