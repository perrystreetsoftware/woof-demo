import DI
import DTO
import Models

@Factory
public final class DogProfileDTOToDomainMapper {
    private let dogMapper: DogDTOToDomainMapper
    private let sizeMapper: DogSizeDTOToDomainMapper

    public func callAsFunction(_ dto: DogProfileDTO) -> DogProfile {
        DogProfile(
            dog: dogMapper(dto.dog),
            breed: dto.breed,
            ageInYears: dto.ageInYears,
            size: sizeMapper(dto.size),
            neighborhood: dto.neighborhood,
            personality: dto.personality,
            favoriteActivity: dto.favoriteActivity,
            bio: dto.bio
        )
    }
}
