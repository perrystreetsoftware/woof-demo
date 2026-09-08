import DI
import Models

@Factory
public final class DogDomainToFavoriteCellUIModelMapper {
    public func callAsFunction(dog: Dog) -> FavoriteCellUIModel {
        FavoriteCellUIModel(
            id: dog.id,
            name: dog.name,
            photoUrl: dog.photoUrl,
            domain: dog
        )
    }
}
