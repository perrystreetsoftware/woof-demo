import SwiftUI

public struct AtomTag: View {
    @Environment(\.theme) private var theme

    private let text: String
    private let styleRole: TagStyleRole

    public init(text: String, styleRole: TagStyleRole = .neutral) {
        self.text = text
        self.styleRole = styleRole
    }

    public var body: some View {
        Text(text)
            .font(theme.typography.subhead.p3)
            .foregroundStyle(styleRole.textColor(from: theme))
            .lineLimit(1)
            .padding(.horizontal, PaddingRoles.Element.relaxed.rawValue)
            .padding(.vertical, PaddingRoles.Element.compact.rawValue)
            .background(styleRole.backgroundColor(from: theme), in: RoundedRectangle(cornerRadius: theme.radius.xl))
    }
}
