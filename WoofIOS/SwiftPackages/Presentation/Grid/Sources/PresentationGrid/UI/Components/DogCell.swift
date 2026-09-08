import DesignSystem
import PresentationCommon
import Resources
import SwiftUI

struct DogCell: View {
    let cell: DogCellUIModel
    let onCellAppear: (DogCellUIModel) -> Void
    let onCellTap: (DogCellUIModel) -> Void

    var body: some View {
        AsyncImageStateProvider(url: cell.photoUrl) { imageState in
            OrgPhotoCard(
                title: cell.name,
                imageState: imageState,
                contentDescription: L10n.Accessibility.dogPhoto(cell.name),
                onTap: { onCellTap(cell) }
            )
        }
        .onAppear {
            onCellAppear(cell)
        }
    }
}
