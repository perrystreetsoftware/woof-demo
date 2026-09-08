import SwiftUI

public struct AtomHeroScrim: View {
    @Environment(\.theme) private var theme

    public init() {}

    public var body: some View {
        LinearGradient(
            stops: [
                .init(color: Color.clear, location: 0),
                .init(color: Color.clear, location: 0.45),
                .init(color: theme.colors.scrimDim, location: 1),
            ],
            startPoint: .top,
            endPoint: .bottom
        )
        .allowsHitTesting(false)
    }
}
