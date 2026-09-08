import DI
import DTO
import Models

@Factory
public final class DogDTOToDomainMapper {
    public func callAsFunction(_ dto: DogDTO) -> Dog {
        Dog(
            id: dto.id,
            name: dto.name,
            photoUrl: dto.photoUrl
        )
    }
}
