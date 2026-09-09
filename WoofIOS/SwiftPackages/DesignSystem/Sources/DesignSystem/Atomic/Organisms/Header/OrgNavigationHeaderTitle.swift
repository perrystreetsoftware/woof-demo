import SwiftUI

public struct OrgNavigationHeaderTitle: View {
    @Environment(\.theme) private var theme

    private let title: String

    public init(title: String) {
        self.title = title
    }

    public var body: some View {
        HStack(spacing: 0) {
            AtomText(text: title, textFontRole: .displayH2, maxLines: 1)
        }
        .frame(maxWidth: .infinity, minHeight: SizingRoles.InteractionHeight.comfort.rawValue, alignment: .leading)
        .background(theme.colors.background)
    }
}
