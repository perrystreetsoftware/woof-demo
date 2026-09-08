import SwiftUI

struct AtomPainterImage: View {
    private let image: Image
    private let contentDescription: String?

    init(image: Image, contentDescription: String?) {
        self.image = image
        self.contentDescription = contentDescription
    }

    var body: some View {
        Color.clear
            .overlay {
                image
                    .resizable()
                    .scaledToFill()
            }
            .clipped()
            .contentShape(Rectangle())
            .accessibilityLabel(contentDescription ?? "")
    }
}
