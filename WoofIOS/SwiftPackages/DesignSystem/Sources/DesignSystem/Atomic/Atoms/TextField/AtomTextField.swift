import SwiftUI

public struct AtomTextField: View {
    @Environment(\.theme) private var theme

    private let text: String
    private let placeholder: String
    private let onTextChange: (String) -> Void
    private let onSubmit: () -> Void
    private let textFontRole: TextFontRole
    private let colorRole: TextColorRole
    private let placeholderColorRole: TextColorRole

    public init(
        text: String,
        placeholder: String,
        onTextChange: @escaping (String) -> Void,
        onSubmit: @escaping () -> Void,
        textFontRole: TextFontRole,
        colorRole: TextColorRole,
        placeholderColorRole: TextColorRole
    ) {
        self.text = text
        self.placeholder = placeholder
        self.onTextChange = onTextChange
        self.onSubmit = onSubmit
        self.textFontRole = textFontRole
        self.colorRole = colorRole
        self.placeholderColorRole = placeholderColorRole
    }

    public var body: some View {
        TextField(
            "",
            text: Binding(get: { text }, set: onTextChange),
            prompt: Text(placeholder).foregroundStyle(placeholderColorRole.color(from: theme))
        )
        .font(textFontRole.font(from: theme))
        .foregroundStyle(colorRole.color(from: theme))
        .tint(theme.colors.primary)
        .submitLabel(.send)
        .onSubmit(onSubmit)
    }
}
