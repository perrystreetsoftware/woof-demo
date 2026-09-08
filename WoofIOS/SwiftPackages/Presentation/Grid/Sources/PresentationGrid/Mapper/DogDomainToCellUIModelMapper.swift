import DI
import Models

@Factory
public final class DogDomainToCellUIModelMapper {
    public func callAsFunction(dog: Dog, index: Int) -> DogCellUIModel {
        DogCellUIModel(
            index: index,
            id: dog.id,
            name: dog.name,
            photoUrl: dog.photoUrl,
            domain: dog
        )
    }
}
