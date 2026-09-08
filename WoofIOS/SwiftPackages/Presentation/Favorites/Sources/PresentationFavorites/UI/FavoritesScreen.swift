import DesignSystem
import PresentationCommon
import Resources
import SwiftUI

public struct FavoritesScreen: View {
    private let state: FavoritesViewModel.State
    private let onCellTap: (FavoriteCellUIModel) -> Void

    public init(state: FavoritesViewModel.State, onCellTap: @escaping (FavoriteCellUIModel) -> Void) {
        self.state = state
        self.onCellTap = onCellTap
    }

    public var body: some View {
        switch state {
        case .empty:
            FavoritesEmptyScreen()
        case .loaded(let cells):
            FavoritesLoadedScreen(cells: cells, onCellTap: onCellTap)
        }
    }
}

private struct FavoritesEmptyScreen: View {
    var body: some View {
        TemplateCenteredContent(topBar: { OrgNavigationHeaderTitle(title: L10n.Favorites.title) }) {
            OrgEmptyState(
                title: L10n.Favorites.emptyTitle,
                message: L10n.Favorites.emptyMessage
            )
        }
    }
}

private struct FavoritesLoadedScreen: View {
    let cells: [FavoriteCellUIModel]
    let onCellTap: (FavoriteCellUIModel) -> Void

    var body: some View {
        TemplateGrid(topBar: { OrgNavigationHeaderTitle(title: L10n.Favorites.title) }) {
            ForEach(cells) { cell in
                AsyncImageStateProvider(url: cell.photoUrl) { imageState in
                    OrgPhotoCard(
                        title: cell.name,
                        imageState: imageState,
                        contentDescription: L10n.Accessibility.dogPhoto(cell.name),
                        onTap: { onCellTap(cell) }
                    )
                }
            }
        }
    }
}

#Preview("Loaded") {
    ThemedScreenPreview(theme: WoofTheme.light()) {
        FavoritesScreen(state: FavoritesPreviewData.loaded(), onCellTap: { _ in })
    }
}

#Preview("Empty") {
    ThemedScreenPreview(theme: WoofTheme.dark()) {
        FavoritesScreen(state: .empty, onCellTap: { _ in })
    }
}
