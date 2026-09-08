import PresentationCommon
import Resources

struct GridErrorToToastMapper: ErrorToToastMapping {
    func callAsFunction(_ error: Swift.Error) -> ErrorToast? {
        ErrorToast(message: L10n.Grid.errorMessage)
    }
}
