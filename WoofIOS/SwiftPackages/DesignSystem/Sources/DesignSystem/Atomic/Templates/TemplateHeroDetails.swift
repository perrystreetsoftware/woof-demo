import SwiftUI

public struct TemplateHeroDetails<Hero: View, TopBar: View, Summary: View, BottomBar: View, Overlay: View, Details: View>: View {
    @Environment(\.theme) private var theme
    @Environment(\.templateSafeAreaInsets) private var inheritedInsets
    @State private var scrollOffset: CGFloat = 0
    @State private var summaryHeight: CGFloat = 0
    @State private var bottomBarHeight: CGFloat = 0

    private let hero: (CGFloat) -> Hero
    private let topBar: () -> TopBar
    private let summary: () -> Summary
    private let bottomBar: () -> BottomBar
    private let overlay: () -> Overlay
    private let details: () -> Details

    public init(
        @ViewBuilder hero: @escaping (CGFloat) -> Hero,
        @ViewBuilder topBar: @escaping () -> TopBar,
        @ViewBuilder summary: @escaping () -> Summary,
        @ViewBuilder bottomBar: @escaping () -> BottomBar,
        @ViewBuilder overlay: @escaping () -> Overlay,
        @ViewBuilder details: @escaping () -> Details
    ) {
        self.hero = hero
        self.topBar = topBar
        self.summary = summary
        self.bottomBar = bottomBar
        self.overlay = overlay
        self.details = details
    }

    public var body: some View {
        GeometryReader { proxy in
            let topInset = max(proxy.safeAreaInsets.top, inheritedInsets.top)
            let bottomInset = max(proxy.safeAreaInsets.bottom, inheritedInsets.bottom)
            let containerHeight = proxy.size.height + proxy.safeAreaInsets.top + proxy.safeAreaInsets.bottom
            let topBarHeight = SizingRoles.InteractionHeight.comfort.rawValue + topInset
            let panelBottomPadding = PaddingRoles.Screen.regular.rawValue
            let panelMinHeight = summaryHeight + bottomBarHeight + panelBottomPadding
            let maxOffset = max(containerHeight - topBarHeight - panelMinHeight, 1)
            let progress = min(max(scrollOffset / maxOffset, 0), 1)
            ZStack(alignment: .top) {
                hero(progress)
                    .frame(width: proxy.size.width, height: containerHeight)
                ScrollView {
                    VStack(alignment: .leading, spacing: SpacingRoles.Module.compact.rawValue) {
                        Color.clear
                            .frame(height: maxOffset + topBarHeight)
                        summary()
                            .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { summaryHeight = $0 }
                        Color.clear
                            .frame(height: bottomBarHeight * (1 - progress))
                        details()
                    }
                    .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
                    .padding(.bottom, bottomBarHeight + panelBottomPadding)
                }
                .scrollIndicators(.hidden)
                .scrollTargetBehavior(HeroDetailsScrollConnection(maxOffset: maxOffset))
                .onScrollGeometryChange(for: CGFloat.self) { $0.contentOffset.y + $0.contentInsets.top } action: { _, offset in
                    scrollOffset = offset
                }
                .frame(width: proxy.size.width, height: containerHeight)
                topBar()
                    .padding(.top, topInset)
                VStack(spacing: 0) {
                    Spacer(minLength: 0)
                    bottomBar()
                        .padding(.bottom, bottomInset)
                        .onGeometryChange(for: CGFloat.self) { $0.size.height } action: { bottomBarHeight = $0 }
                        .background {
                            LinearGradient(
                                colors: [Color.clear, theme.colors.scrimDim],
                                startPoint: .top,
                                endPoint: .bottom
                            )
                        }
                }
                .frame(height: containerHeight)
                overlay()
            }
            .frame(width: proxy.size.width, height: containerHeight)
            .ignoresSafeArea(.container)
        }
        .background(theme.colors.background.ignoresSafeArea())
    }
}
