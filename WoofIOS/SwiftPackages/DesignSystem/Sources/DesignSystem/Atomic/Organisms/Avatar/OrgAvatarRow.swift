import SwiftUI

public struct OrgAvatarRow: View {
    private let title: String
    private let subtitle: String
    private let imageState: AsyncImageState
    private let contentDescription: String

    public init(title: String, subtitle: String, imageState: AsyncImageState, contentDescription: String) {
        self.title = title
        self.subtitle = subtitle
        self.imageState = imageState
        self.contentDescription = contentDescription
    }

    public var body: some View {
        HStack(spacing: SpacingRoles.Component.regular.rawValue) {
            MolAsyncImage(state: imageState, contentDescription: contentDescription)
                .frame(width: SizingRoles.Avatar.l.rawValue, height: SizingRoles.Avatar.l.rawValue)
                .clipShape(Circle())
            VStack(alignment: .leading, spacing: SpacingRoles.Component.extraCompact.rawValue) {
                AtomText(text: title, textFontRole: .displayH2, maxLines: 1)
                AtomText(text: subtitle, textFontRole: .bodyP2, colorRole: .onSurfaceVariant, maxLines: 2)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}
