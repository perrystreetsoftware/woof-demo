import SwiftUI

public struct MolTagGroupPlaceholder: View {
    private static let sampleTagRoles: [TagPlaceholderRole] = [.expanded, .regular, .compact]

    public init() {}

    public var body: some View {
        HStack(spacing: SpacingRoles.Component.compact.rawValue) {
            ForEach(Self.sampleTagRoles, id: \.self) { role in
                AtomPlaceholder(role: role)
            }
        }
    }
}
