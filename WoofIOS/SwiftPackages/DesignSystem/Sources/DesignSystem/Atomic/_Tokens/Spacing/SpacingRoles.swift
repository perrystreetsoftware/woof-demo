import Foundation

public enum SpacingRoles {
    public enum Component {
        case hairline
        case extraCompact
        case compact
        case cozy
        case regular
        case relaxed
        case expanded
        case extraExpanded

        public var rawValue: CGFloat {
            switch self {
            case .hairline: SpacingPrimitives.space2.rawValue
            case .extraCompact: SpacingPrimitives.space4.rawValue
            case .compact: SpacingPrimitives.space8.rawValue
            case .cozy: SpacingPrimitives.space12.rawValue
            case .regular: SpacingPrimitives.space16.rawValue
            case .relaxed: SpacingPrimitives.space20.rawValue
            case .expanded: SpacingPrimitives.space24.rawValue
            case .extraExpanded: SpacingPrimitives.space32.rawValue
            }
        }
    }

    public enum Module {
        case compact
        case regular
        case expanded

        public var rawValue: CGFloat {
            switch self {
            case .compact: SpacingPrimitives.space20.rawValue
            case .regular: SpacingPrimitives.space40.rawValue
            case .expanded: SpacingPrimitives.space60.rawValue
            }
        }
    }
}
