import SwiftUI

public enum ButtonBackgroundRole {
    case none
    case scrimContainer

    public func color(from theme: ThemeImplementing) -> Color {
        switch self {
        case .none: Color.clear
        case .scrimContainer: theme.colors.scrimContainer
        }
    }
}
