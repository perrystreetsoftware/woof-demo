import PresentationCommon
import PresentationNavigation
import SwiftUI

public struct ProfileViewAdapter: View {
    @StateObject private var headerViewModel: ProfileHeaderViewModel
    @StateObject private var detailsViewModel: ProfileDetailsViewModel
    @StateObject private var woofViewModel: ProfileWoofViewModel
    @StateObject private var messageViewModel: ProfileMessageViewModel
    @StateObject private var moderationViewModel: ProfileModerationViewModel
    @Environment(\.navigator) private var navigator

    private let page: ProfilePageUIModel

    public init(page: ProfilePageUIModel) {
        self.page = page
        _headerViewModel = .init(arg1: page.domain)
        _detailsViewModel = .init(arg1: page.domain)
        _woofViewModel = .init(arg1: page.domain)
        _messageViewModel = .init(arg1: page.domain)
        _moderationViewModel = .init(arg1: page.domain)
    }

    public var body: some View {
        ProfileViewScreen(
            page: page,
            headerState: headerViewModel.state,
            detailsState: detailsViewModel.state,
            woofState: woofViewModel.state,
            messageState: messageViewModel.state,
            moderationState: moderationViewModel.state,
            onBackTap: navigator.back,
            onFavoriteTap: headerViewModel.onFavoriteTap,
            onOverflowExpandedChange: headerViewModel.onOverflowExpandedChange,
            onOverflowItemTap: onOverflowItemTap,
            onWoofTap: woofViewModel.onWoofTap,
            onWoofToastDismiss: woofViewModel.onToastDismiss,
            onMessageTextChange: messageViewModel.onTextChange,
            onMessageSendTap: messageViewModel.onSendTap,
            onMessageToastDismiss: messageViewModel.onToastDismiss,
            onModerationDialogConfirm: moderationViewModel.onDialogConfirm,
            onModerationDialogDismiss: moderationViewModel.onDialogDismiss,
            onModerationToastDismiss: moderationViewModel.onToastDismiss
        )
        .errorAdapter(
            sources: [
                ErrorSource(headerViewModel),
                ErrorSource(detailsViewModel),
                ErrorSource(woofViewModel),
                ErrorSource(messageViewModel),
                ErrorSource(moderationViewModel),
            ],
            errorMapper: ProfileErrorToToastMapper(name: page.name)
        )
        .onAppear {
            headerViewModel.onViewAppear()
            detailsViewModel.onViewAppear()
            woofViewModel.onViewAppear()
            messageViewModel.onViewAppear()
            moderationViewModel.onViewAppear()
        }
    }

    private func onOverflowItemTap(_ item: ProfileOverflowMenuItemUIModel) {
        headerViewModel.onOverflowExpandedChange(false)
        switch item {
        case .report: moderationViewModel.onReportTap()
        case .block: moderationViewModel.onBlockTap()
        }
    }
}
