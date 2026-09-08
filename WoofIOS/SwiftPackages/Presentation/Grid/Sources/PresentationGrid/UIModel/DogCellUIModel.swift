import Models

public struct DogCellUIModel: Hashable, Identifiable {
    public let index: Int
    public let id: Int
    public let name: String
    public let photoUrl: String
    let domain: Dog

    init(index: Int, id: Int, name: String, photoUrl: String, domain: Dog) {
        self.index = index
        self.id = id
        self.name = name
        self.photoUrl = photoUrl
        self.domain = domain
    }
}
