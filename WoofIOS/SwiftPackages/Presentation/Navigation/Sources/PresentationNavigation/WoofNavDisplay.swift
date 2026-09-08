import SwiftUI
import Utils

public struct WoofNavDisplay: View {
    @StateObject private var backStack: WoofBackStack
    @Inject private var navigator: NavigatorImplementing
    @Inject private var routerEntries: [RouterEntryImplementing]

    public init() {
        _backStack = .init()
    }

    public var body: some View {
        NavigationStack(path: path) {
            destinationView(for: .home)
                .navigationDestination(for: WoofDestination.self) { destination in
                    destinationView(for: destination)
                        .toolbar(.hidden, for: .navigationBar)
                }
                .toolbar(.hidden, for: .navigationBar)
        }
        .environment(\.navigator, navigator)
    }

    private var path: Binding<[WoofDestination]> {
        Binding(
            get: { Array(backStack.entries.dropFirst()) },
            set: { backStack.popTo(count: $0.count + 1) }
        )
    }

    private func destinationView(for destination: WoofDestination) -> AnyView {
        routerEntries.first { $0.canHandle(destination) }?.view(for: destination) ?? AnyView(EmptyView())
    }
}
