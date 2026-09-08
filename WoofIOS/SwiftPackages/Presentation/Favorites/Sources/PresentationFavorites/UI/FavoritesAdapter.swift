import PresentationNavigation
import SwiftUI

public struct FavoritesAdapter: View {
    @StateObject private var viewModel: FavoritesViewModel
    @Environment(\.navigator) private var navigator

    public init() {
        _viewModel = .init()
    }

    public var body: some View {
        FavoritesScreen(
            state: viewModel.state,
            onCellTap: { cell in navigator.goTo(.profile(dogId: cell.id)) }
        )
        .onAppear {
            viewModel.onViewAppear()
        }
    }
}
