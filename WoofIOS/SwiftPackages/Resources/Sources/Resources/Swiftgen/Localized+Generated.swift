import Foundation

public enum L10n {
    public static let appName = L10n.tr("app_name")

    public enum Accessibility {
        public static let back = L10n.tr("accessibility_back")
        public static let moreOptions = L10n.tr("accessibility_more_options")
        public static let addFavorite = L10n.tr("accessibility_add_favorite")
        public static let removeFavorite = L10n.tr("accessibility_remove_favorite")
        public static let woof = L10n.tr("accessibility_woof")
        public static let sendMessage = L10n.tr("accessibility_send_message")
        public static let appLogo = L10n.tr("accessibility_app_logo")

        public static func dogPhoto(_ p1: String) -> String {
            L10n.tr("accessibility_dog_photo", p1)
        }
    }

    public enum Home {
        public enum Tab {
            public static let browse = L10n.tr("home_tab_browse")
            public static let favorites = L10n.tr("home_tab_favorites")
            public static let account = L10n.tr("home_tab_account")
        }
    }

    public enum Favorites {
        public static let title = L10n.tr("favorites_title")
        public static let emptyTitle = L10n.tr("favorites_empty_title")
        public static let emptyMessage = L10n.tr("favorites_empty_message")
    }

    public enum Account {
        public static let title = L10n.tr("account_title")
    }

    public enum Grid {
        public static let errorTitle = L10n.tr("grid_error_title")
        public static let errorMessage = L10n.tr("grid_error_message")
        public static let errorRetry = L10n.tr("grid_error_retry")
    }

    public enum Profile {
        public static let sectionPersonality = L10n.tr("profile_section_personality")
        public static let sectionDetails = L10n.tr("profile_section_details")
        public static let labelBreed = L10n.tr("profile_label_breed")
        public static let labelAge = L10n.tr("profile_label_age")
        public static let labelSize = L10n.tr("profile_label_size")
        public static let labelNeighborhood = L10n.tr("profile_label_neighborhood")
        public static let labelFavoriteActivity = L10n.tr("profile_label_favorite_activity")
        public static let sizeSmall = L10n.tr("profile_size_small")
        public static let sizeMedium = L10n.tr("profile_size_medium")
        public static let sizeLarge = L10n.tr("profile_size_large")
        public static let menuReport = L10n.tr("profile_menu_report")
        public static let menuBlock = L10n.tr("profile_menu_block")
        public static let reportMessage = L10n.tr("profile_report_message")
        public static let reportConfirm = L10n.tr("profile_report_confirm")
        public static let blockConfirm = L10n.tr("profile_block_confirm")
        public static let dialogCancel = L10n.tr("profile_dialog_cancel")
        public static let toastReportSent = L10n.tr("profile_toast_report_sent")
        public static let toastMessageEmpty = L10n.tr("profile_toast_message_empty")

        public static func messagePlaceholder(_ p1: String) -> String {
            L10n.tr("profile_message_placeholder", p1)
        }

        public static func summary(_ p1: String, _ p2: String, _ p3: String) -> String {
            L10n.tr("profile_summary", p1, p2, p3)
        }

        public static func age(_ count: Int) -> String {
            L10n.plural("profile_age", count)
        }

        public static func sectionAbout(_ p1: String) -> String {
            L10n.tr("profile_section_about", p1)
        }

        public static func reportTitle(_ p1: String) -> String {
            L10n.tr("profile_report_title", p1)
        }

        public static func blockTitle(_ p1: String) -> String {
            L10n.tr("profile_block_title", p1)
        }

        public static func blockMessage(_ p1: String) -> String {
            L10n.tr("profile_block_message", p1)
        }

        public static func toastMessageSent(_ p1: String) -> String {
            L10n.tr("profile_toast_message_sent", p1)
        }

        public static func toastWoofSent(_ p1: String) -> String {
            L10n.tr("profile_toast_woof_sent", p1)
        }

        public static func toastAlreadyWoofed(_ p1: String) -> String {
            L10n.tr("profile_toast_already_woofed", p1)
        }
    }

    private static func tr(_ key: String, _ args: CVarArg...) -> String {
        let format = NSLocalizedString(key, bundle: BundleToken.bundle, comment: "")
        return String(format: format, locale: Locale.current, arguments: args)
    }

    private static func plural(_ key: String, _ count: Int) -> String {
        let format = NSLocalizedString(key, bundle: BundleToken.bundle, comment: "")
        return String.localizedStringWithFormat(format, count)
    }
}

private final class BundleToken {
    static let bundle: Bundle = {
        #if SWIFT_PACKAGE
            return Bundle.module
        #else
            return Bundle(for: BundleToken.self)
        #endif
    }()
}
