import Foundation

public enum ButtonState {
    case enabled
    case disabled
    case loading

    public func alpha(from theme: ThemeImplementing) -> Double {
        switch self {
        case .enabled: theme.alpha.enabled
        case .disabled: theme.alpha.disabled
        case .loading: theme.alpha.enabled
        }
    }

    public var isClickable: Bool {
        switch self {
        case .enabled: true
        case .disabled: false
        case .loading: false
        }
    }

    public var showsLoadingIndicator: Bool {
        switch self {
        case .enabled: false
        case .disabled: false
        case .loading: true
        }
    }
}
