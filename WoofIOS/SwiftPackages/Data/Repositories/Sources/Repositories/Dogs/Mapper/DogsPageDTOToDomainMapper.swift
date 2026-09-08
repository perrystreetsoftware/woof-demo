import DI
import DTO
import Models

@Factory
public final class DogsPageDTOToDomainMapper {
    private let dogMapper: DogDTOToDomainMapper

    public func callAsFunction(_ dto: DogsPageDTO) -> DogsPage {
        DogsPage(
            dogs: dto.results.map { dogMapper($0) },
            offset: dto.offset,
            total: dto.total
        )
    }
}
