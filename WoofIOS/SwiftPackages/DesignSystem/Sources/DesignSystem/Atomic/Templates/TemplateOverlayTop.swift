import SwiftUI

public struct TemplateOverlayTop<Content: View>: View {
    private let content: () -> Content

    public init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content
    }

    public var body: some View {
        ZStack(alignment: .top) {
            content()
        }
        .padding(.top, PaddingRoles.Screen.expanded.rawValue)
        .padding(.horizontal, PaddingRoles.Screen.regular.rawValue)
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .allowsHitTesting(false)
    }
}
