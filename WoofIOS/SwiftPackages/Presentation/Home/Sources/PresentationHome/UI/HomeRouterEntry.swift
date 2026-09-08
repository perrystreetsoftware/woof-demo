import DI
import PresentationNavigation
import SwiftUI

@FactoryCollection
public final class HomeRouterEntry: RouterEntryImplementing {
    public init() {}

    public func canHandle(_ destination: WoofDestination) -> Bool {
        destination == .home
    }

    public func view(for destination: WoofDestination) -> AnyView {
        AnyView(HomeAdapter())
    }
}
