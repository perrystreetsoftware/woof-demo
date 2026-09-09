import SwiftUI

public struct OrgPhotoCard: View {
    @Environment(\.theme) private var theme

    private let title: String
    private let imageState: AsyncImageState
    private let contentDescription: String
    private let onTap: () -> Void

    public init(title: String, imageState: AsyncImageState, contentDescription: String, onTap: @escaping () -> Void) {
        self.title = title
        self.imageState = imageState
        self.contentDescription = contentDescription
        self.onTap = onTap
    }

    public var body: some View {
        Button(action: onTap) {
            ZStack(alignment: .bottomLeading) {
                MolAsyncImage(state: imageState, contentDescription: contentDescription)
                AtomHeroScrim()
                AtomText(text: title, textFontRole: .subheadP2, colorRole: .onScrim, maxLines: 1)
                    .padding(PaddingRoles.Element.regular.rawValue)
            }
            .aspectRatio(theme.aspectRatios.gridCell, contentMode: .fit)
            .background(theme.colors.placeholder)
            .clipShape(RoundedRectangle(cornerRadius: theme.radius.s))
        }
        .buttonStyle(.plain)
    }
}
