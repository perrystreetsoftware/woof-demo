import SwiftUI

public struct MolTagGroupPlaceholder: View {
    private static let sampleTags = ["Placeholder", "Sample tag", "Tag"]

    public init() {}

    public var body: some View {
        HStack(spacing: SpacingRoles.Component.compact.rawValue) {
            ForEach(Self.sampleTags, id: \.self) { tag in
                AtomTagPlaceholder(text: tag)
            }
        }
        .atomPlaceholderShimmer()
    }
}
