import SwiftUI

public enum ButtonRole {
    case primary
    case secondary
    case destructive

    public func surfaceColor(from theme: ThemeImplementing) -> Color {
        switch self {
        case .primary: theme.colors.primary
        case .secondary: theme.colors.surfaceContainerHigh
        case .destructive: theme.colors.destructive
        }
    }

    public var textColorRole: TextColorRole {
        switch self {
        case .primary: .onPrimary
        case .secondary: .onSurface
        case .destructive: .onPrimary
        }
    }

    public var loadingColorRole: IconColorRole {
        switch self {
        case .primary: .onPrimary
        case .secondary: .onSurface
        case .destructive: .onPrimary
        }
    }
}
