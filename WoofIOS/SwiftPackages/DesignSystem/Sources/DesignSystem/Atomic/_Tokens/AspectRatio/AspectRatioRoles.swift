import Foundation

public struct AspectRatioRoles {
    public let gridCell: CGFloat
    public let square: CGFloat

    public static let `default` = AspectRatioRoles(
        gridCell: 0.75,
        square: 1
    )
}
