import Foundation

public struct Dog: Hashable {
    public let id: Int
    public let name: String
    public let photoUrl: String

    public init(id: Int, name: String, photoUrl: String) {
        self.id = id
        self.name = name
        self.photoUrl = photoUrl
    }
}
