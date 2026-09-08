import Resources

extension ProfileToastUIModel {
    var text: String {
        switch self {
        case .woofSent(let name): L10n.Profile.toastWoofSent(name)
        case .messageSent(let name): L10n.Profile.toastMessageSent(name)
        case .reportSent: L10n.Profile.toastReportSent
        }
    }
}
