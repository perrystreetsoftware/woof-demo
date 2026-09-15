import SwiftUI

public struct GradientRoles {
    public let scrimVerticalDelayed: LinearGradient
    public let scrimVertical: LinearGradient

    static func from(_ colors: Colors) -> GradientRoles {
        GradientRoles(
            scrimVerticalDelayed: LinearGradient(
                stops: [
                    .init(color: Color.clear, location: 0),
                    .init(color: Color.clear, location: 0.45),
                    .init(color: colors.scrimDim, location: 1),
                ],
                startPoint: .top,
                endPoint: .bottom
            ),
            scrimVertical: LinearGradient(
                colors: [Color.clear, colors.scrimDim],
                startPoint: .top,
                endPoint: .bottom
            )
        )
    }
}
