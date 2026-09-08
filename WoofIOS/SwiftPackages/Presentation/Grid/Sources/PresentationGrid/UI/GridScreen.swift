import DesignSystem
import Resources
import SwiftUI

public struct GridScreen: View {
    private let state: GridViewModel.State
    private let onCellAppear: (DogCellUIModel) -> Void
    private let onCellTap: (DogCellUIModel) -> Void
    private let onRetryTap: () -> Void

    public init(
        state: GridViewModel.State,
        onCellAppear: @escaping (DogCellUIModel) -> Void,
        onCellTap: @escaping (DogCellUIModel) -> Void,
        onRetryTap: @escaping () -> Void
    ) {
        self.state = state
        self.onCellAppear = onCellAppear
        self.onCellTap = onCellTap
        self.onRetryTap = onRetryTap
    }

    public var body: some View {
        switch state {
        case .loading:
            GridLoadingScreen()
        case .loaded(let loaded):
            GridLoadedScreen(state: loaded, onCellAppear: onCellAppear, onCellTap: onCellTap)
        case .error:
            GridErrorScreen(onRetryTap: onRetryTap)
        }
    }
}

private struct GridLoadingScreen: View {
    private static let placeholderCellCount = 30

    var body: some View {
        TemplateGrid(topBar: { OrgNavigationHeaderBranded() }) {
            ForEach(0..<Self.placeholderCellCount, id: \.self) { _ in
                MolCardPlaceholder()
            }
        }
    }
}

private struct GridLoadedScreen: View {
    let state: GridViewModel.State.Loaded
    let onCellAppear: (DogCellUIModel) -> Void
    let onCellTap: (DogCellUIModel) -> Void

    var body: some View {
        TemplateGrid(topBar: { OrgNavigationHeaderBranded() }) {
            ForEach(state.cells) { cell in
                DogCell(cell: cell, onCellAppear: onCellAppear, onCellTap: onCellTap)
            }
            ForEach(0..<state.loadingMoreCellCount, id: \.self) { _ in
                MolCardPlaceholder()
            }
        }
    }
}

private struct GridErrorScreen: View {
    let onRetryTap: () -> Void

    var body: some View {
        TemplateCenteredContent(topBar: { OrgNavigationHeaderBranded() }) {
            OrgErrorState(
                title: L10n.Grid.errorTitle,
                message: L10n.Grid.errorMessage,
                actionText: L10n.Grid.errorRetry,
                onActionTap: onRetryTap
            )
        }
    }
}

#Preview("Loaded") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        GridScreen(state: GridPreviewData.loaded(), onCellAppear: { _ in }, onCellTap: { _ in }, onRetryTap: {})
    }
}

#Preview("Loading") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        GridScreen(state: .loading, onCellAppear: { _ in }, onCellTap: { _ in }, onRetryTap: {})
    }
}

#Preview("Error") {
    ThemedScreenPreview(theme: WoofTheme.dark()) {
        GridScreen(state: .error, onCellAppear: { _ in }, onCellTap: { _ in }, onRetryTap: {})
    }
}
