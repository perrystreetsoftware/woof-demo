import Resources
import SwiftUI

public struct OrgBottomNavigationItem<Value: Hashable>: Identifiable {
    public let icon: ImageAsset
    public let label: String
    public let value: Value
    public let isSelected: Bool
    public let onTap: () -> Void

    public var id: Value { value }

    public init(icon: ImageAsset, label: String, value: Value, isSelected: Bool, onTap: @escaping () -> Void) {
        self.icon = icon
        self.label = label
        self.value = value
        self.isSelected = isSelected
        self.onTap = onTap
    }
}

public struct OrgBottomNavigationBarLabel: View {
    @Environment(\.theme) private var theme

    private let icon: ImageAsset
    private let label: String
    private let isSelected: Bool

    public init(icon: ImageAsset, label: String, isSelected: Bool) {
        self.icon = icon
        self.label = label
        self.isSelected = isSelected
    }

    public var body: some View {
        Label {
            AtomText(text: label, textFontRole: .subheadP3, colorRole: labelColorRole, maxLines: 1)
        } icon: {
            AtomIcon(icon: icon, iconSize: .m, colorRole: iconColorRole)
        }
    }

    private var iconColorRole: IconColorRole {
        switch isSelected {
        case true: .primary
        case false: .onSurfaceVariant
        }
    }

    private var labelColorRole: TextColorRole {
        switch isSelected {
        case true: .primary
        case false: .onSurfaceVariant
        }
    }
}
