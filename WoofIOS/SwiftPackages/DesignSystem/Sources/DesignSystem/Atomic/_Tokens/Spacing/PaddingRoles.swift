import Foundation

public struct PaddingRoles {
    public let screenHorizontal: CGFloat
    public let screenTopRegular: CGFloat
    public let screenBottomRegular: CGFloat
    public let elementCompact: CGFloat
    public let elementRegular: CGFloat
    public let elementRelaxed: CGFloat
    public let elementExpanded: CGFloat
    public let elementExtraExpanded: CGFloat

    public static let `default` = PaddingRoles(
        screenHorizontal: SpacingPrimitives.space20.rawValue,
        screenTopRegular: SpacingPrimitives.space60.rawValue,
        screenBottomRegular: SpacingPrimitives.space40.rawValue,
        elementCompact: SpacingPrimitives.space4.rawValue,
        elementRegular: SpacingPrimitives.space8.rawValue,
        elementRelaxed: SpacingPrimitives.space12.rawValue,
        elementExpanded: SpacingPrimitives.space20.rawValue,
        elementExtraExpanded: SpacingPrimitives.space24.rawValue
    )
}
