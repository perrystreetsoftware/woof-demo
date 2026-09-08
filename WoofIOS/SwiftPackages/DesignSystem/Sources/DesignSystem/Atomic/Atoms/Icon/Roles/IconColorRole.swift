import SwiftUI

public enum IconColorRole {
    case onSurface
    case onSurfaceVariant
    case onPrimary
    case onScrim
    case onScrimVariant
    case primary
    case recent
    case destructive

    public func color(from theme: ThemeImplementing) -> Color {
        switch self {
        case .onSurface: theme.colors.onSurface
        case .onSurfaceVariant: theme.colors.onSurfaceVariant
        case .onPrimary: theme.colors.onPrimary
        case .onScrim: theme.colors.onScrim
        case .onScrimVariant: theme.colors.onScrimVariant
        case .primary: theme.colors.primary
        case .recent: theme.colors.recent
        case .destructive: theme.colors.destructive
        }
    }
}
