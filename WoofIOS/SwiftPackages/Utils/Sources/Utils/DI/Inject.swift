import SwiftUI
import Swinject

@propertyWrapper
public struct Inject<Value> {
    public private(set) var wrappedValue: Value

    public init() {
        self.init(name: nil, resolver: nil)
    }

    public init(name: String? = nil, resolver: Resolver? = nil) {
        guard let resolver = resolver ?? InjectSettings.resolver else {
            fatalError("Make sure InjectSettings.resolver is set!")
        }

        guard let value = resolver.resolve(Value.self, name: name) else {
            fatalError("Could not resolve non-optional \(Value.self)")
        }

        wrappedValue = value
    }
}

public extension StateObject {
    init(name: String? = nil, resolver: Resolver? = nil) {
        self.init(
            wrappedValue: requireResolved(resolver) {
                $0.resolve(ObjectType.self, name: name)
            })
    }

    init<Arg1>(name: String? = nil, resolver: Resolver? = nil, arg1: Arg1) {
        self.init(
            wrappedValue: requireResolved(resolver) {
                $0.resolve(ObjectType.self, name: name, argument: arg1)
            })
    }

    init<Arg1, Arg2>(name: String? = nil, resolver: Resolver? = nil, arg1: Arg1, arg2: Arg2) {
        self.init(
            wrappedValue: requireResolved(resolver) {
                $0.resolve(ObjectType.self, name: name, arguments: arg1, arg2)
            })
    }
}

private func requireResolved<Object>(_ resolver: Resolver?, _ resolve: (Resolver) -> Object?) -> Object {
    guard let resolver = resolver ?? InjectSettings.resolver else {
        fatalError("Make sure InjectSettings.resolver is set!")
    }

    guard let object = resolve(resolver) else {
        fatalError("Could not resolve non-optional \(Object.self)")
    }

    return object
}
