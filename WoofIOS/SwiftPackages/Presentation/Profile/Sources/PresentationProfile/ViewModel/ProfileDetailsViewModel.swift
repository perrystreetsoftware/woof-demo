import Combine
import DI
import Models
import PresentationCommon
import UseCase
import Utils

@Factory
public final class ProfileDetailsViewModel: StateProducingViewModel<ProfileDetailsUIModel, DogsError> {
    @DIArgument private let dog: Dog
    private let getDogProfileUseCase: GetDogProfileUseCase
    private let profileMapper: DogProfileDomainToUIModelMapper

    init(
        dog: Dog,
        getDogProfileUseCase: GetDogProfileUseCase,
        profileMapper: DogProfileDomainToUIModelMapper
    ) {
        self.dog = dog
        self.getDogProfileUseCase = getDogProfileUseCase
        self.profileMapper = profileMapper
        super.init(
            initialValue: ProfileDetailsUIModel(
                name: dog.name,
                summary: nil,
                heroTags: [],
                content: .loading
            )
        )
    }

    public override func onFirstAppear() {
        getDogProfileUseCase(dog: dog)
            .pss_sink { [weak self] result in
                guard let self else { return }
                switch result {
                case .success(let profile): setState(profileMapper(profile: profile))
                case .failure(let error): emitError(error)
                }
            }
            .store(in: &cancellables)
    }
}
