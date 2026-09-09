import Foundation

public struct WoofTheme: ThemeImplementing {
    public let name: String
    public let isDark: Bool
    public let colors: Colors
    public let alpha: AlphaRoles = .default
    public let radius: RadiusRoles = .default
    public let typography: Typography = .default
    public let motion: MotionRoles = .default
    public let aspectRatios: AspectRatioRoles = .default

    private init(name: String, isDark: Bool, colors: Colors) {
        self.name = name
        self.isDark = isDark
        self.colors = colors
    }

    public static func light() -> ThemeImplementing {
        WoofTheme(name: "Light", isDark: false, colors: ColorRoles.light)
    }

    public static func dark() -> ThemeImplementing {
        WoofTheme(name: "Dark", isDark: true, colors: ColorRoles.dark)
    }
}

extension Typography {
    static let `default` = TypographyRoles.default
}
