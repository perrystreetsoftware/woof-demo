import DI
import Foundation

@SingleForProtocol
public final class WoofNavigator: NavigatorImplementing {
    private let backStack: WoofBackStack

    public func goTo(_ destination: WoofDestination) {
        backStack.push(destination)
    }

    public func back() {
        backStack.pop()
    }
}
