@attached(member, names: arbitrary) // TODO: the name should be just init, we're only generating the initializer. Just does not work for some reason.
public macro DI() = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(peer, names: arbitrary)
public macro DINamed<T>(_ name: T) = #externalMacro(module: "DIMacros", type: "DINamedMacro")

@attached(peer, names: arbitrary)
public macro DINamed() = #externalMacro(module: "DIMacros", type: "DINamedMacro")

@attached(peer, names: arbitrary)
public macro DIArgument() = #externalMacro(module: "DIMacros", type: "DIArgumentMacro")

@attached(member, names: arbitrary)
public macro Factory() = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(member, names: arbitrary)
public macro Factory<T>(_ param: T) = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(member, names: arbitrary)
public macro WeakFactory() = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(member, names: arbitrary)
public macro FactoryForProtocol() = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(member, names: arbitrary)
public macro SingleForProtocol() = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(member, names: arbitrary)
public macro MockApi() = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(member, names: arbitrary)
public macro FactoryCollection() = #externalMacro(module: "DIMacros", type: "FactoryCollectionMacro")

@attached(member, names: arbitrary)
public macro Single() = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(member, names: arbitrary)
public macro Single<T>(_ param: T) = #externalMacro(module: "DIMacros", type: "DIMacro")

@attached(member, names: named(allCases))
public macro StaticLetCaseIterable() = #externalMacro(module: "DIMacros", type: "StaticLetCaseIterableMacro")

@attached(member, names: named(copy))
public macro StructCopyable() = #externalMacro(module: "DIMacros", type: "StructCopyableMacro")
