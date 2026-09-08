import SwiftUI

public protocol RouterEntryImplementing {
    func canHandle(_ destination: WoofDestination) -> Bool
    func view(for destination: WoofDestination) -> AnyView
}
