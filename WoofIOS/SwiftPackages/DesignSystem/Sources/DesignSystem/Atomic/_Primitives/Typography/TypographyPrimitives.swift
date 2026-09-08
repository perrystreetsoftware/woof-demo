import SwiftUI

public enum TypographyPrimitives {
    public enum Bold {
        public static let size32 = style(size: 32, weight: .bold)
        public static let size24 = style(size: 24, weight: .bold)
        public static let size20 = style(size: 20, weight: .bold)
        public static let size16 = style(size: 16, weight: .bold)
        public static let size14 = style(size: 14, weight: .bold)
        public static let size12 = style(size: 12, weight: .bold)
    }

    public enum SemiBold {
        public static let size16 = style(size: 16, weight: .semibold)
        public static let size14 = style(size: 14, weight: .semibold)
        public static let size12 = style(size: 12, weight: .semibold)
    }

    public enum Regular {
        public static let size16 = style(size: 16, weight: .regular)
        public static let size14 = style(size: 14, weight: .regular)
        public static let size12 = style(size: 12, weight: .regular)
        public static let size10 = style(size: 10, weight: .regular)
    }

    private static func style(size: CGFloat, weight: Font.Weight) -> Font {
        Font.system(size: size, weight: weight)
    }
}
