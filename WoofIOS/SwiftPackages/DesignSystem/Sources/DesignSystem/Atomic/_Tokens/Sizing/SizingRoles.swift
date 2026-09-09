import Foundation

public enum SizingRoles {
    public enum Icon {
        case xs
        case s
        case m
        case l
        case xl
        case xxl

        public var rawValue: CGFloat {
            switch self {
            case .xs: SizingPrimitives.size12.rawValue
            case .s: SizingPrimitives.size16.rawValue
            case .m: SizingPrimitives.size24.rawValue
            case .l: SizingPrimitives.size32.rawValue
            case .xl: SizingPrimitives.size44.rawValue
            case .xxl: SizingPrimitives.size108.rawValue
            }
        }
    }

    public enum Avatar {
        case s
        case m
        case l

        public var rawValue: CGFloat {
            switch self {
            case .s: SizingPrimitives.size40.rawValue
            case .m: SizingPrimitives.size60.rawValue
            case .l: SizingPrimitives.size88.rawValue
            }
        }
    }

    public enum HorizontalRule {
        case xs
        case s

        public var rawValue: CGFloat {
            switch self {
            case .xs: SizingPrimitives.size1.rawValue
            case .s: SizingPrimitives.size2.rawValue
            }
        }
    }

    public enum InteractionHeight {
        case compact
        case `default`
        case comfort

        public var rawValue: CGFloat {
            switch self {
            case .compact: SizingPrimitives.size32.rawValue
            case .default: SizingPrimitives.size48.rawValue
            case .comfort: SizingPrimitives.size56.rawValue
            }
        }
    }

    public enum GridCell {
        public static let minWidth: CGFloat = SizingPrimitives.size128.rawValue
    }
}
