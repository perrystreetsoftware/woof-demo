import Foundation

public struct ErrorToast: Equatable {
    public let message: String

    public init(message: String) {
        self.message = message
    }
}
