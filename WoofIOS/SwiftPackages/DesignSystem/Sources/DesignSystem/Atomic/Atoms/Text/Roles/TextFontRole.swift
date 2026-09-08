import SwiftUI

public enum TextFontRole {
    case displayH1
    case displayH2
    case displayH3
    case displayH4
    case displayH5
    case displayH6
    case subheadP1
    case subheadP2
    case subheadP3
    case bodyP1
    case bodyP2
    case bodyP3
    case bodyP4

    public func font(from theme: ThemeImplementing) -> Font {
        switch self {
        case .displayH1: theme.typography.display.h1
        case .displayH2: theme.typography.display.h2
        case .displayH3: theme.typography.display.h3
        case .displayH4: theme.typography.display.h4
        case .displayH5: theme.typography.display.h5
        case .displayH6: theme.typography.display.h6
        case .subheadP1: theme.typography.subhead.p1
        case .subheadP2: theme.typography.subhead.p2
        case .subheadP3: theme.typography.subhead.p3
        case .bodyP1: theme.typography.body.p1
        case .bodyP2: theme.typography.body.p2
        case .bodyP3: theme.typography.body.p3
        case .bodyP4: theme.typography.body.p4
        }
    }
}
