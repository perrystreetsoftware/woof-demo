import DesignSystem
import PresentationCommon
import Resources
import SwiftUI

public struct ProfileViewScreen: View {
    private let page: ProfilePageUIModel
    private let headerState: ProfileHeaderViewModel.State
    private let detailsState: ProfileDetailsUIModel
    private let woofState: ProfileWoofViewModel.State
    private let messageState: ProfileMessageViewModel.State
    private let moderationState: ProfileModerationViewModel.State
    private let onBackTap: () -> Void
    private let onFavoriteTap: () -> Void
    private let onOverflowExpandedChange: (Bool) -> Void
    private let onOverflowItemTap: (ProfileOverflowMenuItemUIModel) -> Void
    private let onWoofTap: () -> Void
    private let onWoofToastDismiss: () -> Void
    private let onMessageTextChange: (String) -> Void
    private let onMessageSendTap: () -> Void
    private let onMessageToastDismiss: () -> Void
    private let onModerationDialogConfirm: () -> Void
    private let onModerationDialogDismiss: () -> Void
    private let onModerationToastDismiss: () -> Void

    public init(
        page: ProfilePageUIModel,
        headerState: ProfileHeaderViewModel.State,
        detailsState: ProfileDetailsUIModel,
        woofState: ProfileWoofViewModel.State,
        messageState: ProfileMessageViewModel.State,
        moderationState: ProfileModerationViewModel.State,
        onBackTap: @escaping () -> Void,
        onFavoriteTap: @escaping () -> Void,
        onOverflowExpandedChange: @escaping (Bool) -> Void,
        onOverflowItemTap: @escaping (ProfileOverflowMenuItemUIModel) -> Void,
        onWoofTap: @escaping () -> Void,
        onWoofToastDismiss: @escaping () -> Void,
        onMessageTextChange: @escaping (String) -> Void,
        onMessageSendTap: @escaping () -> Void,
        onMessageToastDismiss: @escaping () -> Void,
        onModerationDialogConfirm: @escaping () -> Void,
        onModerationDialogDismiss: @escaping () -> Void,
        onModerationToastDismiss: @escaping () -> Void
    ) {
        self.page = page
        self.headerState = headerState
        self.detailsState = detailsState
        self.woofState = woofState
        self.messageState = messageState
        self.moderationState = moderationState
        self.onBackTap = onBackTap
        self.onFavoriteTap = onFavoriteTap
        self.onOverflowExpandedChange = onOverflowExpandedChange
        self.onOverflowItemTap = onOverflowItemTap
        self.onWoofTap = onWoofTap
        self.onWoofToastDismiss = onWoofToastDismiss
        self.onMessageTextChange = onMessageTextChange
        self.onMessageSendTap = onMessageSendTap
        self.onMessageToastDismiss = onMessageToastDismiss
        self.onModerationDialogConfirm = onModerationDialogConfirm
        self.onModerationDialogDismiss = onModerationDialogDismiss
        self.onModerationToastDismiss = onModerationToastDismiss
    }

    public var body: some View {
        TemplateHeroDetails(
            hero: { dimProgress in
                AsyncImageStateProvider(url: page.photoUrl) { imageState in
                    OrgHeroPhoto(
                        imageState: imageState,
                        contentDescription: L10n.Accessibility.dogPhoto(page.name),
                        dimProgress: dimProgress
                    )
                }
            },
            topBar: {
                OrgNavigationHeaderOverlay(
                    onBackTap: onBackTap,
                    actions: [
                        OrgNavigationHeaderActionItem(
                            role: .favorite,
                            onTap: onFavoriteTap,
                            isActive: headerState.isFavorite
                        )
                    ],
                    overflowItems: headerState.overflowItems.map { item in
                        item.toOverflowMenuItem(onTap: { onOverflowItemTap(item) })
                    },
                    isOverflowExpanded: headerState.isOverflowExpanded,
                    onOverflowExpandedChange: onOverflowExpandedChange
                )
            },
            summary: {
                OrgHeroSummary(
                    title: detailsState.name,
                    subtitle: detailsState.summary?.text,
                    tags: detailsState.heroTags
                )
            },
            bottomBar: {
                OrgTypeBarWithAction(
                    text: messageState.text,
                    placeholder: L10n.Profile.messagePlaceholder(page.name),
                    onTextChange: onMessageTextChange,
                    onSubmit: onMessageSendTap,
                    actionRole: .woof,
                    isActionActive: woofState.hasWoofed,
                    onActionTap: onWoofTap,
                    isSubmitEnabled: messageState.isSendEnabled
                )
            },
            overlay: {
                OrgToastHost(message: woofState.toast?.text, onDismiss: onWoofToastDismiss)
                OrgToastHost(message: messageState.toast?.text, onDismiss: onMessageToastDismiss)
                OrgToastHost(message: moderationState.toast?.text, onDismiss: onModerationToastDismiss)
            },
            details: {
                switch detailsState.content {
                case .loading:
                    OrgSectionsPlaceholder()
                case .visible(let sections):
                    ForEach(sections, id: \.self) { section in
                        ProfileSection(section: section)
                    }
                }
            }
        )
        .profileModerationDialog(
            dialog: moderationState.dialog,
            onConfirmTap: onModerationDialogConfirm,
            onDismissTap: onModerationDialogDismiss
        )
    }
}

#Preview("Loaded") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        ProfileViewScreen(
            page: ProfilePreviewData.page(),
            headerState: ProfilePreviewData.header(),
            detailsState: ProfilePreviewData.details(),
            woofState: ProfileWoofViewModel.State(hasWoofed: true, toast: nil),
            messageState: .initial,
            moderationState: .initial,
            onBackTap: {},
            onFavoriteTap: {},
            onOverflowExpandedChange: { _ in },
            onOverflowItemTap: { _ in },
            onWoofTap: {},
            onWoofToastDismiss: {},
            onMessageTextChange: { _ in },
            onMessageSendTap: {},
            onMessageToastDismiss: {},
            onModerationDialogConfirm: {},
            onModerationDialogDismiss: {},
            onModerationToastDismiss: {}
        )
    }
}

#Preview("Loading") {
    ThemedScreenPreview(theme: WoofTheme.dark()) {
        ProfileViewScreen(
            page: ProfilePreviewData.page(),
            headerState: ProfilePreviewData.header(),
            detailsState: ProfilePreviewData.loadingDetails(),
            woofState: ProfileWoofViewModel.State(hasWoofed: false, toast: nil),
            messageState: .initial,
            moderationState: .initial,
            onBackTap: {},
            onFavoriteTap: {},
            onOverflowExpandedChange: { _ in },
            onOverflowItemTap: { _ in },
            onWoofTap: {},
            onWoofToastDismiss: {},
            onMessageTextChange: { _ in },
            onMessageSendTap: {},
            onMessageToastDismiss: {},
            onModerationDialogConfirm: {},
            onModerationDialogDismiss: {},
            onModerationToastDismiss: {}
        )
    }
}
