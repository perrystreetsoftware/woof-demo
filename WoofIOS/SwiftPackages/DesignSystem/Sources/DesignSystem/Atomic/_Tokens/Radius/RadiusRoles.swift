import Foundation

public struct RadiusRoles {
    public let xs: CGFloat
    public let s: CGFloat
    public let m: CGFloat
    public let l: CGFloat
    public let xl: CGFloat

    public static let `default` = RadiusRoles(
        xs: SizingPrimitives.size2.rawValue,
        s: SizingPrimitives.size4.rawValue,
        m: SizingPrimitives.size8.rawValue,
        l: SizingPrimitives.size12.rawValue,
        xl: SizingPrimitives.size20.rawValue
    )
}
