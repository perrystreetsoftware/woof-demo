import SwiftUI

public struct TemplatePager<Page: View>: View {
    @Environment(\.theme) private var theme
    @State private var scrolledPage: AnyHashable?

    private let pages: [PagerPage]
    private let pageContent: (Int) -> Page

    public init(
        pageCount: Int,
        initialPage: Int,
        key: (Int) -> AnyHashable,
        @ViewBuilder pageContent: @escaping (Int) -> Page
    ) {
        pages = (0..<pageCount).map { PagerPage(index: $0, key: key($0)) }
        self.pageContent = pageContent
        _scrolledPage = State(initialValue: pages.indices.contains(initialPage) ? pages[initialPage].key : nil)
    }

    public var body: some View {
        GeometryReader { proxy in
            ScrollView(.horizontal) {
                LazyHStack(spacing: 0) {
                    ForEach(pages) { page in
                        pageContent(page.index)
                            .containerRelativeFrame(.horizontal)
                            .clipped()
                            .contentShape(Rectangle())
                    }
                }
                .scrollTargetLayout()
            }
            .scrollTargetBehavior(.paging)
            .scrollPosition(id: $scrolledPage)
            .scrollIndicators(.hidden)
            .background(theme.colors.background)
            .environment(\.templateSafeAreaInsets, proxy.safeAreaInsets)
            .ignoresSafeArea(.container)
        }
    }
}

private struct PagerPage: Identifiable {
    let index: Int
    let key: AnyHashable

    var id: AnyHashable { key }
}
