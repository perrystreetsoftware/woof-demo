import SwiftUI

public enum TagStyleRole {
    case neutral
    case onScrim
    case accent

    public func backgroundColor(from theme: ThemeImplementing) -> Color {
        switch self {
        case .neutral: theme.colors.surfaceContainerHigh
        case .onScrim: theme.colors.scrimContainer
        case .accent: theme.colors.primary
        }
    }

    public func textColor(from theme: ThemeImplementing) -> Color {
        switch self {
        case .neutral: theme.colors.onSurface
        case .onScrim: theme.colors.onScrim
        case .accent: theme.colors.onPrimary
        }
    }
}
