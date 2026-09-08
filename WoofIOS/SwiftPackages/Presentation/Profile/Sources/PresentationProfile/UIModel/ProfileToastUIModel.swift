import Foundation

public enum ProfileToastUIModel: Hashable {
    case woofSent(name: String)
    case messageSent(name: String)
    case reportSent
}
