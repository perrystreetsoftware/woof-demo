import Resources

extension ProfileModerationDialogUIModel {
    var title: String {
        switch self {
        case .report(let name): L10n.Profile.reportTitle(name)
        case .block(let name): L10n.Profile.blockTitle(name)
        }
    }

    var message: String {
        switch self {
        case .report: L10n.Profile.reportMessage
        case .block(let name): L10n.Profile.blockMessage(name)
        }
    }

    var confirmText: String {
        switch self {
        case .report: L10n.Profile.reportConfirm
        case .block: L10n.Profile.blockConfirm
        }
    }
}
