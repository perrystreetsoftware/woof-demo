import Foundation

public protocol NavigatorImplementing {
    func goTo(_ destination: WoofDestination)
    func back()
}
