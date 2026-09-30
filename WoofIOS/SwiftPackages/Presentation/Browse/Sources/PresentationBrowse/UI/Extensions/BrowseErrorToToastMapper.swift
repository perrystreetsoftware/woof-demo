import PresentationCommon
import Resources

struct BrowseErrorToToastMapper: ErrorToToastMapping {
    func callAsFunction(_ error: Swift.Error) -> ErrorToast? {
        ErrorToast(message: L10n.Browse.errorMessage)
    }
}
