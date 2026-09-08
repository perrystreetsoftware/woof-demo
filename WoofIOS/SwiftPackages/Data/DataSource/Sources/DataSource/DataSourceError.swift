import Foundation

public enum DataSourceError: Error, Equatable {
    case unavailable(String)
    case notFound(dogId: Int)
}
