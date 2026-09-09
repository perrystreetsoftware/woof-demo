import SwiftUI

public enum SectionToneRole {
    case onScrim
    case onSurface

    public var titleColorRole: TextColorRole {
        switch self {
        case .onScrim: .onScrim
        case .onSurface: .onSurface
        }
    }

    public var bodyColorRole: TextColorRole {
        switch self {
        case .onScrim: .onScrim
        case .onSurface: .onSurface
        }
    }

    public var labelColorRole: TextColorRole {
        switch self {
        case .onScrim: .onScrimVariant
        case .onSurface: .onSurfaceVariant
        }
    }

    public var tagStyleRole: TagStyleRole {
        switch self {
        case .onScrim: .onScrim
        case .onSurface: .neutral
        }
    }

    public func containerColor(from theme: ThemeImplementing) -> Color {
        switch self {
        case .onScrim: Color.clear
        case .onSurface: theme.colors.surfaceContainer
        }
    }

    public func containerPadding(from theme: ThemeImplementing) -> CGFloat {
        switch self {
        case .onScrim: .zero
        case .onSurface: PaddingRoles.Element.expanded.rawValue
        }
    }
}
