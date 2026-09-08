import Combine
import DesignSystem
import Foundation
import ImageIO
import Resources
import SwiftUI

public final class AsyncImageLoader: ObservableObject {
    @Published public private(set) var state: AsyncImageState = .loading

    private let url: String
    private var cancellable: AnyCancellable?

    private static let assetScheme = "woof://"
    private static let decodeQueue = DispatchQueue(label: "com.perrystreet.woof.imageloader", qos: .userInitiated)

    public init(url: String) {
        self.url = url
    }

    public func load() {
        guard cancellable == nil else { return }
        cancellable = Just(url)
            .receive(on: Self.decodeQueue)
            .map { Self.decode(url: $0) }
            .receive(on: DispatchQueue.main)
            .sink { [weak self] image in
                guard let image else { return }
                self?.state = AsyncImageState(image: image, isLoading: false)
            }
    }

    private static func decode(url: String) -> Image? {
        guard let fileURL = resolve(url: url),
            let source = CGImageSourceCreateWithURL(fileURL as CFURL, nil),
            let cgImage = CGImageSourceCreateImageAtIndex(source, 0, nil)
        else {
            return nil
        }
        return Image(decorative: cgImage, scale: 1)
    }

    private static func resolve(url: String) -> URL? {
        guard url.hasPrefix(assetScheme) else {
            return URL(string: url)
        }
        let path = String(url.dropFirst(assetScheme.count))
        return Asset.Dogs.url(fileName: (path as NSString).lastPathComponent)
    }
}
