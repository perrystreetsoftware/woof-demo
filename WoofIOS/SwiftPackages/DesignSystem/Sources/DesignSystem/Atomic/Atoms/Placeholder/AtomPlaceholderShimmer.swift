import SwiftUI

public struct AtomPlaceholderShimmer: ViewModifier {
    @Environment(\.theme) private var theme
    @State private var progress: CGFloat = 0

    public init() {}

    public func body(content: Content) -> some View {
        content
            .overlay {
                GeometryReader { proxy in
                    LinearGradient(
                        colors: [
                            Color.clear,
                            theme.colors.onPlaceholder.opacity(theme.alpha.shimmerLow),
                            theme.colors.onPlaceholder.opacity(theme.alpha.shimmerLowMedium),
                            theme.colors.onPlaceholder.opacity(theme.alpha.shimmerLow),
                            Color.clear,
                        ],
                        startPoint: .topLeading,
                        endPoint: .bottomTrailing
                    )
                    .frame(width: proxy.size.width)
                    .offset(x: -proxy.size.width + progress * proxy.size.width * 2)
                }
                .mask(content)
                .allowsHitTesting(false)
            }
            .onAppear {
                withAnimation(.linear(duration: theme.motion.shimmerDuration).repeatForever(autoreverses: false)) {
                    progress = 1
                }
            }
    }
}

public extension View {
    func atomPlaceholderShimmer() -> some View {
        modifier(AtomPlaceholderShimmer())
    }
}
