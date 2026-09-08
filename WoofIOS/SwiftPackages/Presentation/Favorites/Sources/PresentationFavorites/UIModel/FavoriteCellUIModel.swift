import Models

public struct FavoriteCellUIModel: Hashable, Identifiable {
    public let id: Int
    public let name: String
    public let photoUrl: String
    let domain: Dog

    init(id: Int, name: String, photoUrl: String, domain: Dog) {
        self.id = id
        self.name = name
        self.photoUrl = photoUrl
        self.domain = domain
    }
}
