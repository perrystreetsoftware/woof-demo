import SwiftUI

public struct MolAsyncImage: View {
    @Environment(\.theme) private var theme

    private let state: AsyncImageState
    private let contentDescription: String?

    public init(state: AsyncImageState, contentDescription: String?) {
        self.state = state
        self.contentDescription = contentDescription
    }

    public var body: some View {
        ZStack {
            AtomPainterImage(image: state.image, contentDescription: contentDescription)
            AtomRoundedRectanglePlaceholder()
                .atomPlaceholderShimmer()
                .opacity(state.isLoading ? theme.alpha.enabled : 0)
                .animation(.easeOut(duration: theme.motion.durationShort), value: state.isLoading)
        }
    }
}
