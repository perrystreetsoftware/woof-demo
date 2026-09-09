import SwiftUI

public struct OrgNavigationHeaderBranded: View {
    @Environment(\.theme) private var theme

    public init() {}

    public var body: some View {
        HStack(spacing: 0) {
            MolBrandLogo()
        }
        .frame(maxWidth: .infinity, minHeight: SizingRoles.InteractionHeight.comfort.rawValue, alignment: .leading)
        .background(theme.colors.background)
    }
}
