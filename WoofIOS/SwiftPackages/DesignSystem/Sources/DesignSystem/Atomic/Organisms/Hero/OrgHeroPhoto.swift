import SwiftUI

public struct OrgHeroPhoto: View {
    private let imageState: AsyncImageState
    private let contentDescription: String
    private let dimProgress: CGFloat

    public init(imageState: AsyncImageState, contentDescription: String, dimProgress: CGFloat) {
        self.imageState = imageState
        self.contentDescription = contentDescription
        self.dimProgress = dimProgress
    }

    public var body: some View {
        ZStack {
            MolAsyncImage(state: imageState, contentDescription: contentDescription)
            AtomHeroScrim()
            AtomScrim(dimProgress: dimProgress)
        }
    }
}
