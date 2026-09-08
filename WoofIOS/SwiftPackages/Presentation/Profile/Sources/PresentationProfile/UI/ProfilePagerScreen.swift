import DesignSystem
import PresentationCommon
import SwiftUI

public struct ProfilePagerScreen<PageContent: View>: View {
    private let state: ProfilePagerViewModel.State
    private let pageContent: (ProfilePageUIModel) -> PageContent

    public init(state: ProfilePagerViewModel.State, @ViewBuilder pageContent: @escaping (ProfilePageUIModel) -> PageContent) {
        self.state = state
        self.pageContent = pageContent
    }

    public var body: some View {
        viewGuard(!state.pages.isEmpty) {
            TemplatePager(
                pageCount: state.pages.count,
                initialPage: state.initialPage,
                key: { page in state.pages[page].id }
            ) { page in
                pageContent(state.pages[page])
            }
        }
    }
}

#Preview("Pager") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        ProfilePagerScreen(state: ProfilePagerViewModel.State(pages: [ProfilePreviewData.page()], initialPage: 0)) { page in
            OrgErrorState(title: page.name, message: page.photoUrl, actionText: "", onActionTap: {})
        }
    }
}
