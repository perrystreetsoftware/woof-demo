import SwiftUI

public struct OrgNavigationHeaderBranded: View {
    @Environment(\.theme) private var theme

    public init() {}

    public var body: some View {
        HStack(spacing: 0) {
            MolBrandLogo()
        }
        .padding(.horizontal, theme.padding.screenHorizontal)
        .frame(maxWidth: .infinity, minHeight: theme.sizing.interactionHeightComfort, alignment: .leading)
        .background(theme.colors.background)
    }
}
