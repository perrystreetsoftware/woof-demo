import DI
import PresentationNavigation
import SwiftUI

@FactoryCollection
public final class ProfileRouterEntry: RouterEntryImplementing {
    public init() {}

    public func canHandle(_ destination: WoofDestination) -> Bool {
        switch destination {
        case .profile: true
        case .home: false
        }
    }

    public func view(for destination: WoofDestination) -> AnyView {
        switch destination {
        case .profile(let dogId): AnyView(ProfilePagerAdapter(dogId: dogId))
        case .home: AnyView(EmptyView())
        }
    }
}
