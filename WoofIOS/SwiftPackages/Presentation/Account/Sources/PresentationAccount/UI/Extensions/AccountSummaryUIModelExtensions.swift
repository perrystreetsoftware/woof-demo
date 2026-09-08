import Resources

extension AccountSummaryUIModel {
    var text: String {
        L10n.Profile.summary(L10n.Profile.age(ageInYears), breed, neighborhood)
    }
}
