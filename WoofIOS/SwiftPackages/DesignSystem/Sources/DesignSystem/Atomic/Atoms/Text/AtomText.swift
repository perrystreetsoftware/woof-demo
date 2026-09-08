import SwiftUI

public struct AtomText: View {
    @Environment(\.theme) private var theme

    private let text: String
    private let textFontRole: TextFontRole
    private let colorRole: TextColorRole
    private let maxLines: Int?
    private let textAlign: TextAlignment

    public init(
        text: String,
        textFontRole: TextFontRole,
        colorRole: TextColorRole = .onSurface,
        maxLines: Int? = nil,
        textAlign: TextAlignment = .leading
    ) {
        self.text = text
        self.textFontRole = textFontRole
        self.colorRole = colorRole
        self.maxLines = maxLines
        self.textAlign = textAlign
    }

    public var body: some View {
        Text(text)
            .font(textFontRole.font(from: theme))
            .foregroundStyle(colorRole.color(from: theme))
            .lineLimit(maxLines)
            .truncationMode(.tail)
            .multilineTextAlignment(textAlign)
    }
}
