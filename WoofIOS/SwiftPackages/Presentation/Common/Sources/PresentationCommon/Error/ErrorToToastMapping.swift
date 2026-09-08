import Foundation

public protocol ErrorToToastMapping {
    func callAsFunction(_ error: Swift.Error) -> ErrorToast?
}
