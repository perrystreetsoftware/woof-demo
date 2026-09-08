import Foundation

public enum ProfileModerationDialogUIModel: Hashable {
    case report(name: String)
    case block(name: String)

    public var name: String {
        switch self {
        case .report(let name): name
        case .block(let name): name
        }
    }
}
