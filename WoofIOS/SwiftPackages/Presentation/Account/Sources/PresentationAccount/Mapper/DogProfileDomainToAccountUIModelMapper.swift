import DI
import Models

@Factory
public final class DogProfileDomainToAccountUIModelMapper {
    public func callAsFunction(profile: DogProfile) -> AccountUIModel {
        AccountUIModel(
            name: profile.dog.name,
            photoUrl: profile.dog.photoUrl,
            summary: AccountSummaryUIModel(
                ageInYears: profile.ageInYears,
                breed: profile.breed,
                neighborhood: profile.neighborhood
            ),
            bio: profile.bio,
            personality: profile.personality,
            rows: [
                .breed(profile.breed),
                .age(profile.ageInYears),
                .size(profile.size),
                .neighborhood(profile.neighborhood),
                .favoriteActivity(profile.favoriteActivity),
            ]
        )
    }
}
