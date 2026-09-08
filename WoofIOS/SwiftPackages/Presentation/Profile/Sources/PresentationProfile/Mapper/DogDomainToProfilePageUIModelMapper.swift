import DI
import Models

@Factory
public final class DogDomainToProfilePageUIModelMapper {
    public func callAsFunction(dog: Dog) -> ProfilePageUIModel {
        ProfilePageUIModel(
            id: dog.id,
            name: dog.name,
            photoUrl: dog.photoUrl,
            domain: dog
        )
    }
}
