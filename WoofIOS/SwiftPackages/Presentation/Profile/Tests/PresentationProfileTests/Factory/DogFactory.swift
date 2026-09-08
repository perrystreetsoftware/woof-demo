import Models

final class DogFactory {
    private var dog = Dog(id: 1, name: "Bruno", photoUrl: "woof://dogs/golden_retriever_01.jpg")

    @discardableResult
    func withId(_ id: Int) -> Self {
        dog = Dog(id: id, name: "Dog \(id)", photoUrl: dog.photoUrl)
        return self
    }

    func produce() -> Dog {
        dog
    }
}
