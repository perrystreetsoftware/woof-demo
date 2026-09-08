import SwiftUI

public struct ImageAsset: Hashable {
    public let systemName: String

    public var image: Image {
        Image(systemName: systemName)
    }
}

public enum Asset {
    public enum Icons {
        public static let logo = ImageAsset(systemName: "pawprint.fill")
        public static let pawFilled = ImageAsset(systemName: "pawprint.fill")
        public static let pawOutline = ImageAsset(systemName: "pawprint")
        public static let arrowBack = ImageAsset(systemName: "chevron.backward")
        public static let moreVertical = ImageAsset(systemName: "ellipsis")
        public static let starFilled = ImageAsset(systemName: "star.fill")
        public static let starOutline = ImageAsset(systemName: "star")
        public static let send = ImageAsset(systemName: "paperplane.fill")
        public static let flag = ImageAsset(systemName: "flag")
        public static let block = ImageAsset(systemName: "hand.raised")
        public static let check = ImageAsset(systemName: "checkmark.circle.fill")
        public static let browseOutline = ImageAsset(systemName: "square.grid.2x2")
        public static let browseFilled = ImageAsset(systemName: "square.grid.2x2.fill")
        public static let accountOutline = ImageAsset(systemName: "person")
        public static let accountFilled = ImageAsset(systemName: "person.fill")
    }

    public enum Dogs {
        public static func url(fileName: String) -> URL? {
            BundleToken.bundle.url(forResource: fileName, withExtension: nil, subdirectory: "Dogs")
        }
    }
}

private final class BundleToken {
    static let bundle: Bundle = {
        #if SWIFT_PACKAGE
            return Bundle.module
        #else
            return Bundle(for: BundleToken.self)
        #endif
    }()
}
