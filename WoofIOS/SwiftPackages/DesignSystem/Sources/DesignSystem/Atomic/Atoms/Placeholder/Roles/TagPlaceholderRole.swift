import SwiftUI

public enum TagPlaceholderRole {
    case expanded
    case regular
    case compact

    public var sampleText: String {
        switch self {
        case .expanded: "Placeholder"
        case .regular: "Sample tag"
        case .compact: "Tag"
        }
    }

    public var horizontalPadding: PaddingRoles.Element { .relaxed }

    public var verticalPadding: PaddingRoles.Element { .compact }

    public func font(from theme: ThemeImplementing) -> Font {
        theme.typography.subhead.p3
    }

    public func radius(from theme: ThemeImplementing) -> CGFloat {
        theme.radius.xl
    }
}
