import Foundation

public protocol ThemeImplementing {
    var name: String { get }
    var isDark: Bool { get }
    var colors: Colors { get }
    var alpha: AlphaRoles { get }
    var radius: RadiusRoles { get }
    var typography: Typography { get }
    var motion: MotionRoles { get }
    var aspectRatios: AspectRatioRoles { get }
}
