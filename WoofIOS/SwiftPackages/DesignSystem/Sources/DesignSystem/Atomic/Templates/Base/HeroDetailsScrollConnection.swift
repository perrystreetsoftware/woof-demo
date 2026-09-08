import SwiftUI

struct HeroDetailsScrollConnection: ScrollTargetBehavior {
    private let maxOffset: CGFloat

    private static let settleVelocityThreshold: CGFloat = 800

    init(maxOffset: CGFloat) {
        self.maxOffset = maxOffset
    }

    func updateTarget(_ target: inout ScrollTarget, context: TargetContext) {
        let offset = target.rect.minY
        guard offset > 0, offset < maxOffset else { return }
        let velocity = context.velocity.dy
        let expandsByVelocity = velocity > Self.settleVelocityThreshold
        let collapsesByVelocity = velocity < -Self.settleVelocityThreshold
        let progress = offset / maxOffset
        let settledOffset: CGFloat =
            switch (expandsByVelocity, collapsesByVelocity, progress >= 0.5) {
            case (true, _, _): maxOffset
            case (false, true, _): 0
            case (false, false, true): maxOffset
            case (false, false, false): 0
            }
        target.rect.origin.y = settledOffset
    }
}
