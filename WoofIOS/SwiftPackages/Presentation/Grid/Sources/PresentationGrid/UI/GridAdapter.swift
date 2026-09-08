import PresentationCommon
import PresentationNavigation
import SwiftUI

public struct GridAdapter: View {
    @StateObject private var viewModel: GridViewModel
    @Environment(\.navigator) private var navigator

    public init() {
        _viewModel = .init()
    }

    public var body: some View {
        GridScreen(
            state: viewModel.state,
            onCellAppear: viewModel.onCellAppear,
            onCellTap: { cell in navigator.goTo(.profile(dogId: cell.id)) },
            onRetryTap: viewModel.onRetryTap
        )
        .errorAdapter(
            sources: [ErrorSource(viewModel)],
            errorMapper: GridErrorToToastMapper()
        )
        .onAppear {
            viewModel.onViewAppear()
        }
    }
}
