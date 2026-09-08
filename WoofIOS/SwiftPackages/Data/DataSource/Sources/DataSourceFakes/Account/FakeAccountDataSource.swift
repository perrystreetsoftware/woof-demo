import Combine
import DI
import DTO
import DataSource
import Foundation
import Utils

@MockApi
public final class FakeAccountDataSource: AccountDataSourceImplementing {
    public var account = DogProfileDTO(
        dog: DogDTO(id: 0, name: "Milo", photoUrl: "woof://dogs/border_collie_01.jpg"),
        breed: "Border Collie",
        ageInYears: 3,
        size: DogSizeDTO.medium,
        neighborhood: "Schöneberg, Berlin",
        personality: ["Frisbee pro", "Early riser"],
        favoriteActivity: "Agility courses",
        bio: "Herds tennis balls for a living."
    )
    public var getAccountError: DataSourceError?

    private let scheduler: SchedulerProviding

    public func getAccount() -> AnyPublisher<DogProfileDTO, DataSourceError> {
        Deferred { [weak self] () -> AnyPublisher<DogProfileDTO, DataSourceError> in
            guard let self else { return Empty().eraseToAnyPublisher() }
            if let getAccountError {
                return Fail(error: getAccountError).eraseToAnyPublisher()
            }
            return Just(account).setFailureType(to: DataSourceError.self).eraseToAnyPublisher()
        }
        .receive(on: scheduler.mainScheduler)
        .eraseToAnyPublisher()
    }
}
