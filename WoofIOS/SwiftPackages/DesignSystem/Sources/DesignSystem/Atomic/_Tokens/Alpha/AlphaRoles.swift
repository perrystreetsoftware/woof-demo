import Foundation

public struct AlphaRoles {
    public let enabled: Double
    public let disabled: Double
    public let pressed: Double
    public let heroDim: Double
    public let shimmerLow: Double
    public let shimmerLowMedium: Double

    public static let `default` = AlphaRoles(
        enabled: AlphaPrimitives.max,
        disabled: AlphaPrimitives.medium,
        pressed: AlphaPrimitives.medium,
        heroDim: AlphaPrimitives.high,
        shimmerLow: AlphaPrimitives.low,
        shimmerLowMedium: AlphaPrimitives.lowMedium
    )
}
