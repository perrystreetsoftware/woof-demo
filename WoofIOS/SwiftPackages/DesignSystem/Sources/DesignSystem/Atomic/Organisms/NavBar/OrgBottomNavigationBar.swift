import SwiftUI

public struct OrgBottomNavigationItem<Value: Hashable>: Identifiable {
    public let role: BottomNavigationRole
    public let value: Value
    public let isSelected: Bool
    public let onTap: () -> Void

    public var id: Value { value }

    public init(role: BottomNavigationRole, value: Value, isSelected: Bool, onTap: @escaping () -> Void) {
        self.role = role
        self.value = value
        self.isSelected = isSelected
        self.onTap = onTap
    }
}

public struct OrgBottomNavigationBarLabel: View {
    @Environment(\.theme) private var theme

    private let role: BottomNavigationRole
    private let isSelected: Bool

    public init(role: BottomNavigationRole, isSelected: Bool) {
        self.role = role
        self.isSelected = isSelected
    }

    public var body: some View {
        Label {
            AtomText(text: role.label, textFontRole: .subheadP3, colorRole: labelColorRole, maxLines: 1)
        } icon: {
            AtomIcon(icon: isSelected ? role.selectedIcon : role.icon, iconSize: .m, colorRole: iconColorRole)
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
