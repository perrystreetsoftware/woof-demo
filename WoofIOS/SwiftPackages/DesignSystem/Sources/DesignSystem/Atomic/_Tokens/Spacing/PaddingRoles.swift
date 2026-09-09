import Foundation

public enum PaddingRoles {
    public enum Screen {
        case extraCompact
        case compact
        case regular
        case expanded

        public var rawValue: CGFloat {
            switch self {
            case .extraCompact: SpacingPrimitives.space4.rawValue
            case .compact: SpacingPrimitives.space8.rawValue
            case .regular: SpacingPrimitives.space20.rawValue
            case .expanded: SpacingPrimitives.space60.rawValue
            }
        }
    }

    public enum Element {
        case compact
        case regular
        case relaxed
        case expanded
        case extraExpanded

        public var rawValue: CGFloat {
            switch self {
            case .compact: SpacingPrimitives.space4.rawValue
            case .regular: SpacingPrimitives.space8.rawValue
            case .relaxed: SpacingPrimitives.space12.rawValue
            case .expanded: SpacingPrimitives.space20.rawValue
            case .extraExpanded: SpacingPrimitives.space24.rawValue
            }
        }
    }
}
