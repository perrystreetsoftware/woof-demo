public enum OverflowMenuState {
    case `default`
    case expanded

    public var isExpanded: Bool {
        switch self {
        case .default: false
        case .expanded: true
        }
    }

    public var colorRole: IconColorRole {
        switch self {
        case .default: .onScrim
        case .expanded: .primary
        }
    }
}
