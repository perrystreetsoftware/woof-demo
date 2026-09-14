import SwiftUI

public struct AtomHeroScrim: View {
    @Environment(\.theme) private var theme

    public init() {}

    public var body: some View {
        theme.gradients.scrimVerticalDelayed
            .allowsHitTesting(false)
    }
}
