import SwiftUI

public enum ShapePlaceholderRole {
    case image
    case card

    public func radius(from theme: ThemeImplementing) -> CGFloat {
        switch self {
        case .image, .card: theme.radius.s
        }
    }

    public func aspectRatio(from theme: ThemeImplementing) -> CGFloat? {
        switch self {
        case .image: nil
        case .card: theme.aspectRatios.gridCell
        }
    }
}
