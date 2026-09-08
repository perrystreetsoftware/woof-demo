import Resources
import SwiftUI

public struct MolDropdownMenuItem: View {
    private let text: String
    private let icon: ImageAsset
    private let onTap: () -> Void
    private let isDestructive: Bool

    public init(text: String, icon: ImageAsset, onTap: @escaping () -> Void, isDestructive: Bool = false) {
        self.text = text
        self.icon = icon
        self.onTap = onTap
        self.isDestructive = isDestructive
    }

    public var body: some View {
        Button(role: isDestructive ? .destructive : nil, action: onTap) {
            Label {
                AtomText(text: text, textFontRole: .bodyP1, colorRole: textColorRole)
            } icon: {
                AtomIcon(icon: icon, iconSize: .m, colorRole: iconColorRole)
            }
        }
    }

    private var textColorRole: TextColorRole {
        switch isDestructive {
        case true: .destructive
        case false: .onSurface
        }
    }

    private var iconColorRole: IconColorRole {
        switch isDestructive {
        case true: .destructive
        case false: .onSurface
        }
    }
}
