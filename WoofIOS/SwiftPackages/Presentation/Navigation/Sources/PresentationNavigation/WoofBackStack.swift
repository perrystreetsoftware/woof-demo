import Combine
import DI
import Foundation

@Single
public final class WoofBackStack: ObservableObject {
    @Published public private(set) var entries: [WoofDestination] = [.home]

    public init() {}

    public func push(_ destination: WoofDestination) {
        entries.append(destination)
    }

    public func pop() {
        if entries.count > 1 {
            entries.removeLast()
        }
    }

    public func popTo(count: Int) {
        entries = Array(entries.prefix(max(count, 1)))
    }
}
