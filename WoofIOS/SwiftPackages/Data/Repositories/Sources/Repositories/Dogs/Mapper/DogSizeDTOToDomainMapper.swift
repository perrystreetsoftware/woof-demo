import DI
import DTO
import Models

@Factory
public final class DogSizeDTOToDomainMapper {
    public func callAsFunction(_ size: String) -> DogSize {
        switch size {
        case DogSizeDTO.small: .small
        case DogSizeDTO.large: .large
        default: .medium
        }
    }
}
