import DesignSystem
import PresentationNavigation
import SwiftUI

struct WoofRootView: View {
    @Environment(\.colorScheme) private var colorScheme

    var body: some View {
        Theme(theme: theme) {
            WoofNavDisplay()
        }
    }

    private var theme: ThemeImplementing {
        switch colorScheme {
        case .dark: WoofTheme.dark()
        default: WoofTheme.light()
        }
    }
}
