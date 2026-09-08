import Models

enum ProfilePreviewData {
    private static let dog = Dog(id: 1, name: "Bruno", photoUrl: "")

    static func page() -> ProfilePageUIModel {
        ProfilePageUIModel(id: dog.id, name: dog.name, photoUrl: dog.photoUrl, domain: dog)
    }

    static func header() -> ProfileHeaderViewModel.State {
        ProfileHeaderViewModel.State(
            name: dog.name,
            isFavorite: true,
            isOverflowExpanded: false,
            overflowItems: ProfileOverflowMenuItemUIModel.allCases
        )
    }

    static func loadingDetails() -> ProfileDetailsUIModel {
        ProfileDetailsUIModel(
            name: dog.name,
            summary: nil,
            heroTags: [],
            content: .loading
        )
    }

    static func details() -> ProfileDetailsUIModel {
        ProfileDetailsUIModel(
            name: dog.name,
            summary: ProfileSummaryUIModel(ageInYears: 4, breed: "Golden Retriever", neighborhood: "Kolonaki, Athens"),
            heroTags: ["Playful", "Water lover", "Snack enthusiast"],
            content: .visible(
                sections: [
                    .about(name: dog.name, bio: "Loves swimming, tennis balls, and stealing snacks."),
                    .personality(tags: ["Playful", "Water lover", "Snack enthusiast", "Goofy"]),
                    .details(
                        rows: [
                            .breed("Golden Retriever"),
                            .age(4),
                            .size(.large),
                            .neighborhood("Kolonaki, Athens"),
                            .favoriteActivity("Swimming at the beach"),
                        ]
                    ),
                ]
            )
        )
    }
}
