import PresentationAccount
import PresentationFavorites
import PresentationGrid
import SwiftUI

public struct HomeAdapter: View {
    @StateObject private var viewModel: HomeViewModel

    public init() {
        _viewModel = .init()
    }

    public var body: some View {
        HomeScreen(
            state: viewModel.state,
            onTabSelect: viewModel.onTabSelect
        ) { tab in
            switch tab {
            case .browse: GridAdapter()
            case .favorites: FavoritesAdapter()
            case .account: AccountAdapter()
            }
        }
        .onAppear {
            viewModel.onViewAppear()
        }
    }
}
