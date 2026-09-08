import DI
import Models

@Factory
public final class DogProfileDomainToUIModelMapper {
    private static let heroTagCount = 3

    public func callAsFunction(profile: DogProfile) -> ProfileDetailsUIModel {
        ProfileDetailsUIModel(
            name: profile.dog.name,
            summary: ProfileSummaryUIModel(
                ageInYears: profile.ageInYears,
                breed: profile.breed,
                neighborhood: profile.neighborhood
            ),
            heroTags: Array(profile.personality.prefix(Self.heroTagCount)),
            content: .visible(
                sections: [
                    .about(name: profile.dog.name, bio: profile.bio),
                    .personality(tags: profile.personality),
                    .details(
                        rows: [
                            .breed(profile.breed),
                            .age(profile.ageInYears),
                            .size(profile.size),
                            .neighborhood(profile.neighborhood),
                            .favoriteActivity(profile.favoriteActivity),
                        ]
                    ),
                ]
            )
        )
    }
}
