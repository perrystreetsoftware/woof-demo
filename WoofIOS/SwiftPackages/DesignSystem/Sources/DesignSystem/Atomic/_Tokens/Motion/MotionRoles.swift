import SwiftUI

public struct MotionRoles {
    public let durationShort: Double
    public let durationEmphasized: Double
    public let shimmerDuration: Double
    public let toastDuration: Double
    public let emphasizedEasing: Animation

    public static let `default` = MotionRoles(
        durationShort: 0.2,
        durationEmphasized: 0.4,
        shimmerDuration: 1.2,
        toastDuration: 2.5,
        emphasizedEasing: .timingCurve(0.2, 0, 0, 1, duration: 0.4)
    )
}
