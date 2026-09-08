import SwiftUI

public struct MolCardPlaceholder: View {
    @Environment(\.theme) private var theme

    public init() {}

    public var body: some View {
        AtomRoundedRectanglePlaceholder()
            .aspectRatio(theme.aspectRatios.gridCell, contentMode: .fit)
            .atomPlaceholderShimmer()
    }
}
