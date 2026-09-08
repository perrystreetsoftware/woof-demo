import SwiftUI

public enum TextColorRole {
    case onSurface
    case onSurfaceVariant
    case onPrimary
    case onScrim
    case onScrimVariant
    case primary
    case destructive

    public func color(from theme: ThemeImplementing) -> Color {
        switch self {
        case .onSurface: theme.colors.onSurface
        case .onSurfaceVariant: theme.colors.onSurfaceVariant
        case .onPrimary: theme.colors.onPrimary
        case .onScrim: theme.colors.onScrim
        case .onScrimVariant: theme.colors.onScrimVariant
        case .primary: theme.colors.primary
        case .destructive: theme.colors.destructive
        }
    }
}
