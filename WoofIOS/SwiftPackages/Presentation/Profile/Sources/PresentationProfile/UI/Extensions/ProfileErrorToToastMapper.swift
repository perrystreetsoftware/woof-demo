import Models
import PresentationCommon
import Resources

struct ProfileErrorToToastMapper: ErrorToToastMapping {
    let name: String

    func callAsFunction(_ error: Swift.Error) -> ErrorToast? {
        switch error {
        case WoofError.alreadyWoofed: ErrorToast(message: L10n.Profile.toastAlreadyWoofed(name))
        case MessageError.emptyMessage: ErrorToast(message: L10n.Profile.toastMessageEmpty)
        default: nil
        }
    }
}
