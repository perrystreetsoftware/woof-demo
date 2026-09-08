import SwiftUI

public enum TextPlaceholderRole {
    case title
    case subtitle
    case body

    public var sampleText: String {
        switch self {
        case .title: "Placeholder title"
        case .subtitle: "Placeholder subtitle text"
        case .body: "Placeholder body text that spans a line"
        }
    }

    public func font(from theme: ThemeImplementing) -> Font {
        switch self {
        case .title: theme.typography.display.h4
        case .subtitle: theme.typography.subhead.p2
        case .body: theme.typography.body.p1
        }
    }
}
