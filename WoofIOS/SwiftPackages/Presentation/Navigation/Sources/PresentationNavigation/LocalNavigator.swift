import SwiftUI

private struct NavigatorKey: EnvironmentKey {
    static let defaultValue: NavigatorImplementing = NoOpNavigator()
}

private struct NoOpNavigator: NavigatorImplementing {
    func goTo(_ destination: WoofDestination) {}
    func back() {}
}

public extension EnvironmentValues {
    var navigator: NavigatorImplementing {
        get { self[NavigatorKey.self] }
        set { self[NavigatorKey.self] = newValue }
    }
}
