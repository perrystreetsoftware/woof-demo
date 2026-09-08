import Swinject

public struct InjectSettings {
    public static var resolver: Swinject.Resolver?

    public static var container: Container? {
        resolver as? Container
    }
}
