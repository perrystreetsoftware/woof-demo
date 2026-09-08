import Resources
import SwiftUI

public struct MolBrandLogo: View {
    public init() {}

    public var body: some View {
        HStack(spacing: SpacingRoles.Component.compact.rawValue) {
            AtomAppLogo()
            AtomText(text: L10n.appName, textFontRole: .displayH2, maxLines: 1)
        }
    }
}
