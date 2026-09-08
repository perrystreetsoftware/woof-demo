import PresentationNavigation

final class FakeNavigator: NavigatorImplementing {
    private(set) var destinations: [WoofDestination] = []
    private(set) var backCount = 0

    func goTo(_ destination: WoofDestination) {
        destinations.append(destination)
    }

    func back() {
        backCount += 1
    }
}
