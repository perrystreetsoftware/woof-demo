import DesignSystem
import SwiftUI

public struct AsyncImageStateProvider<Content: View>: View {
    @StateObject private var loader: AsyncImageLoader

    private let content: (AsyncImageState) -> Content

    public init(url: String, @ViewBuilder content: @escaping (AsyncImageState) -> Content) {
        _loader = StateObject(wrappedValue: AsyncImageLoader(url: url))
        self.content = content
    }

    public var body: some View {
        content(loader.state)
            .onAppear(perform: loader.load)
    }
}
