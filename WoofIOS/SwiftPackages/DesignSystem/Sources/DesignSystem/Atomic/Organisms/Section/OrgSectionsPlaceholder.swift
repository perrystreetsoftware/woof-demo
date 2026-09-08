import SwiftUI

public struct OrgSectionsPlaceholder: View {
    private let sections: Int

    public init(sections: Int = 3) {
        self.sections = sections
    }

    public var body: some View {
        VStack(alignment: .leading, spacing: SpacingRoles.Module.compact.rawValue) {
            ForEach(0..<sections, id: \.self) { _ in
                MolTextBlockPlaceholder()
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}
