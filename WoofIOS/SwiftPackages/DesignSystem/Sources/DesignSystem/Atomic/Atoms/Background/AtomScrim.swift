import SwiftUI

public struct AtomScrim: View {
    @Environment(\.theme) private var theme

    private let dimProgress: CGFloat

    public init(dimProgress: CGFloat) {
        self.dimProgress = dimProgress
    }

    public var body: some View {
        theme.colors.shadow
            .opacity(min(max(dimProgress, 0), 1) * theme.alpha.heroDim)
            .allowsHitTesting(false)
    }
}
