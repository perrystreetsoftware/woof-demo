import SwiftUI

public struct AtomPlaceholder: View {
    @Environment(\.theme) private var theme

    private let variant: Variant

    public init(role: TextPlaceholderRole) {
        self.variant = .text(role)
    }

    public init(role: TagPlaceholderRole) {
        self.variant = .tag(role)
    }

    public init(role: ShapePlaceholderRole) {
        self.variant = .shape(role)
    }

    public var body: some View {
        switch variant {
        case .text(let role):
            Text(role.sampleText)
                .font(role.font(from: theme))
                .lineLimit(1)
                .opacity(0)
                .accessibilityHidden(true)
                .placeholderShimmer(radius: role.radius(from: theme))
        case .tag(let role):
            Text(role.sampleText)
                .font(role.font(from: theme))
                .lineLimit(1)
                .opacity(0)
                .padding(.horizontal, role.horizontalPadding.rawValue)
                .padding(.vertical, role.verticalPadding.rawValue)
                .accessibilityHidden(true)
                .placeholderShimmer(radius: role.radius(from: theme))
        case .shape(let role):
            Color.clear
                .aspectRatio(role.aspectRatio(from: theme), contentMode: .fit)
                .placeholderShimmer(radius: role.radius(from: theme))
        }
    }

    private enum Variant {
        case text(TextPlaceholderRole)
        case tag(TagPlaceholderRole)
        case shape(ShapePlaceholderRole)
    }
}

// Every placeholder reads the same clock and draws the band in global coordinates,
// so one highlight sweeps across the whole screen instead of each placeholder on its own.
private struct PlaceholderShimmer: ViewModifier {
    @Environment(\.theme) private var theme
    @State private var containerWidth: CGFloat = .zero

    let radius: CGFloat

    func body(content: Content) -> some View {
        content
            .background(theme.colors.placeholder, in: RoundedRectangle(cornerRadius: radius))
            .overlay {
                TimelineView(.animation) { timeline in
                    GeometryReader { proxy in
                        sweepHighlight
                            .frame(width: containerWidth)
                            .offset(x: sweepOffset(at: timeline.date, globalMinX: proxy.frame(in: .global).minX))
                    }
                }
                .clipShape(RoundedRectangle(cornerRadius: radius))
                .allowsHitTesting(false)
            }
            .background {
                Color.clear
                    .containerRelativeFrame(.horizontal)
                    .onGeometryChange(for: CGFloat.self) { $0.size.width } action: { containerWidth = $0 }
            }
    }

    private var sweepHighlight: LinearGradient {
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
    }

    private func sweepOffset(at date: Date, globalMinX: CGFloat) -> CGFloat {
        let duration = theme.motion.shimmerDuration
        let progress = date.timeIntervalSinceReferenceDate.truncatingRemainder(dividingBy: duration) / duration
        return -containerWidth + progress * containerWidth * 2 - globalMinX
    }
}

private extension View {
    func placeholderShimmer(radius: CGFloat) -> some View {
        modifier(PlaceholderShimmer(radius: radius))
    }
}
