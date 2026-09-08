import SwiftUI

public struct AsyncImageState {
    public let image: Image
    public let isLoading: Bool

    public init(image: Image, isLoading: Bool) {
        self.image = image
        self.isLoading = isLoading
    }

    public static let loading = AsyncImageState(image: Image(decorative: emptyImage, scale: 1), isLoading: true)

    private static let emptyImage: CGImage = {
        let context = CGContext(
            data: nil,
            width: 1,
            height: 1,
            bitsPerComponent: 8,
            bytesPerRow: 4,
            space: CGColorSpaceCreateDeviceRGB(),
            bitmapInfo: CGImageAlphaInfo.premultipliedLast.rawValue
        )!
        return context.makeImage()!
    }()
}
