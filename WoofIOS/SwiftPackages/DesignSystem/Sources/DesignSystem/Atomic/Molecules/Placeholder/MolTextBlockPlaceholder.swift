import SwiftUI

public struct MolTextBlockPlaceholder: View {
    private let lines: Int

    public init(lines: Int = 3) {
        self.lines = lines
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: SpacingRoles.Component.compact.rawValue) {
            AtomTextPlaceholder(role: .title)
            AtomSpacer(spacing: .extraCompact)
            ForEach(0..<lines, id: \.self) { _ in
                AtomTextPlaceholder(role: .body)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .atomPlaceholderShimmer()
    }
}
