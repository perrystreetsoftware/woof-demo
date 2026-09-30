import DesignSystem
import Resources
import SwiftUI

public struct BrowseScreen: View {
    private let state: BrowseViewModel.State
    private let onCellAppear: (DogCellUIModel) -> Void
    private let onCellTap: (DogCellUIModel) -> Void
    private let onRetryTap: () -> Void

    public init(
        state: BrowseViewModel.State,
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
            BrowseLoadingScreen()
        case .loaded(let loaded):
            BrowseLoadedScreen(state: loaded, onCellAppear: onCellAppear, onCellTap: onCellTap)
        case .error:
            BrowseErrorScreen(onRetryTap: onRetryTap)
        }
    }
}

private struct BrowseLoadingScreen: View {
    private static let placeholderCellCount = 30

    var body: some View {
        TemplateGrid(topBar: { OrgNavigationHeaderBranded() }) {
            ForEach(0..<Self.placeholderCellCount, id: \.self) { _ in
                AtomPlaceholder(role: .card)
            }
        }
    }
}

private struct BrowseLoadedScreen: View {
    let state: BrowseViewModel.State.Loaded
    let onCellAppear: (DogCellUIModel) -> Void
    let onCellTap: (DogCellUIModel) -> Void

    var body: some View {
        TemplateGrid(topBar: { OrgNavigationHeaderBranded() }) {
            ForEach(state.cells) { cell in
                DogCell(cell: cell, onCellAppear: onCellAppear, onCellTap: onCellTap)
            }
            ForEach(0..<state.loadingMoreCellCount, id: \.self) { _ in
                AtomPlaceholder(role: .card)
            }
        }
    }
}

private struct BrowseErrorScreen: View {
    let onRetryTap: () -> Void

    var body: some View {
        TemplateCenteredContent(topBar: { OrgNavigationHeaderBranded() }) {
            OrgErrorState(
                title: L10n.Browse.errorTitle,
                message: L10n.Browse.errorMessage,
                actionText: L10n.Browse.errorRetry,
                onActionTap: onRetryTap
            )
        }
    }
}

#Preview("Loaded") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        BrowseScreen(state: BrowsePreviewData.loaded(), onCellAppear: { _ in }, onCellTap: { _ in }, onRetryTap: {})
    }
}

#Preview("Loading") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        BrowseScreen(state: .loading, onCellAppear: { _ in }, onCellTap: { _ in }, onRetryTap: {})
    }
}

#Preview("Error") {
    ThemedScreenPreview(theme: WoofTheme.dark()) {
        BrowseScreen(state: .error, onCellAppear: { _ in }, onCellTap: { _ in }, onRetryTap: {})
    }
}
