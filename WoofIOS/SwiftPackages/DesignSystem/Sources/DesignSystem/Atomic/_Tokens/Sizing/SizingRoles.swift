import Foundation

public struct SizingRoles {
    public let radiusXS: CGFloat
    public let radiusS: CGFloat
    public let radiusM: CGFloat
    public let radiusL: CGFloat
    public let radiusXL: CGFloat
    public let horizontalRuleXS: CGFloat
    public let horizontalRuleS: CGFloat
    public let interactionHeightCompact: CGFloat
    public let interactionHeightDefault: CGFloat
    public let interactionHeightComfort: CGFloat
    public let heroSummaryMinHeight: CGFloat
    public let gridColumns: Int

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

    public static let `default` = SizingRoles(
        radiusXS: SizingPrimitives.size2.rawValue,
        radiusS: SizingPrimitives.size4.rawValue,
        radiusM: SizingPrimitives.size8.rawValue,
        radiusL: SizingPrimitives.size12.rawValue,
        radiusXL: SizingPrimitives.size20.rawValue,
        horizontalRuleXS: SizingPrimitives.size1.rawValue,
        horizontalRuleS: SizingPrimitives.size2.rawValue,
        interactionHeightCompact: SizingPrimitives.size32.rawValue,
        interactionHeightDefault: SizingPrimitives.size48.rawValue,
        interactionHeightComfort: SizingPrimitives.size56.rawValue,
        heroSummaryMinHeight: SizingPrimitives.size240.rawValue,
        gridColumns: 3
    )
}
