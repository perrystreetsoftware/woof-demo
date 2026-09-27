import PresentationCommon
import PresentationNavigation
import SwiftUI

public struct BrowseAdapter: View {
    @StateObject private var viewModel: BrowseViewModel
    @Environment(\.navigator) private var navigator

    public init() {
        _viewModel = .init()
    }

    public var body: some View {
        BrowseScreen(
            state: viewModel.state,
            onCellAppear: viewModel.onCellAppear,
            onCellTap: { cell in navigator.goTo(.profile(dogId: cell.id)) },
            onRetryTap: viewModel.onRetryTap
        )
        .errorAdapter(
            sources: [ErrorSource(viewModel)],
            errorMapper: BrowseErrorToToastMapper()
        )
        .onAppear {
            viewModel.onViewAppear()
        }
    }
}
