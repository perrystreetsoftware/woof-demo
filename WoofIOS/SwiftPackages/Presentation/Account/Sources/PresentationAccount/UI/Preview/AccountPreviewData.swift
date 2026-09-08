import Models

enum AccountPreviewData {
    static func loaded() -> AccountViewModel.State {
        .loaded(
            account: AccountUIModel(
                name: "Milo",
                photoUrl: "",
                summary: AccountSummaryUIModel(ageInYears: 3, breed: "Border Collie", neighborhood: "Schöneberg, Berlin"),
                bio: "Herds tennis balls for a living.",
                personality: ["Frisbee pro", "Early riser"],
                rows: [
                    .breed("Border Collie"),
                    .age(3),
                    .size(.medium),
                    .neighborhood("Schöneberg, Berlin"),
                    .favoriteActivity("Agility courses"),
                ]
            )
        )
    }
}
