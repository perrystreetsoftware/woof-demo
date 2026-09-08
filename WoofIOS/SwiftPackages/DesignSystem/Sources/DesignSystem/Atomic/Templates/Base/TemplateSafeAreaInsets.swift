import SwiftUI

private struct TemplateSafeAreaInsetsKey: EnvironmentKey {
    static let defaultValue = EdgeInsets()
}

extension EnvironmentValues {
    var templateSafeAreaInsets: EdgeInsets {
        get { self[TemplateSafeAreaInsetsKey.self] }
        set { self[TemplateSafeAreaInsetsKey.self] = newValue }
    }
}
