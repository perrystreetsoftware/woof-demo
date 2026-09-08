import Foundation

func normalizeNameExpression(_ expression: String) -> String {
    if expression.hasPrefix("\"") { return expression }
    if expression.hasSuffix(".rawValue") { return expression }
    return "\(expression).rawValue"
}

func extractAllCasesEnumName(from qualifier: String) -> String? {
    guard qualifier.hasSuffix(".allCases") else { return nil }
    return String(qualifier.dropLast(".allCases".count))
}

private func isEnumType(_ propertyType: String, enumName: String) -> Bool {
    let stripped = propertyType.replacingOccurrences(of: "?", with: "")
    return stripped == enumName
}

private func formatArguments(_ arguments: [String]) -> String {
    arguments.joined(separator: ",\n\t\t\t\t\t")
}

private func buildRegistrationBody(
    registerPrefix: String,
    className: String,
    arguments: [String],
    scopeText: String,
    constructorArgumentName: String?
) -> String {
    let closureArguments = constructorArgumentName.map { "resolver, \($0)" } ?? "resolver"
    return
        "\(registerPrefix) { \(closureArguments) in\n\t\t\t\t\(className)(\n\t\t\t\t\t\(formatArguments(arguments)))\n\t\t\t}\n\t\t\t.inObjectScope(\(scopeText))"
}

func buildCustomRegistration(
    className: String,
    registrationTypeName: String,
    scopeText: String,
    interfaceName: String?,
    properties: [StoredPropertyDIInfo]
) -> String {
    let registrationTarget = interfaceName ?? registrationTypeName
    let constructorArgumentName = properties.first(where: { $0.isConstructorArgument })?.name
    let arguments = properties.map { property in
        if property.isConstructorArgument {
            return "\(property.name): \(property.name)"
        }
        if let qualifier = property.namedQualifier {
            return "\(property.name): resolver.resolve(\(property.type).self, name: \(normalizeNameExpression(qualifier)))!"
        }
        return "\(property.name): resolver~>"
    }

    return buildRegistrationBody(
        registerPrefix: "self.register(\(registrationTarget).self)",
        className: className,
        arguments: arguments,
        scopeText: scopeText,
        constructorArgumentName: constructorArgumentName
    )
}

func buildConstructorArgumentAutoregistration(
    className: String,
    registrationTypeName: String,
    scopeText: String,
    interfaceName: String?,
    constructorArgumentType: String
) -> String {
    let registrationTarget = interfaceName ?? registrationTypeName
    return
        "self.autoregister(\(registrationTarget).self, argument: \(constructorArgumentType).self, initializer: \(className).init).inObjectScope(\(scopeText))"
}

func buildAllCasesRegistration(
    className: String,
    scopeText: String,
    interfaceName: String?,
    properties: [StoredPropertyDIInfo],
    enumName: String
) -> String {
    let registrationTarget = interfaceName ?? className
    let constructorArgumentName = properties.first(where: { $0.isConstructorArgument })?.name
    let arguments = properties.map { property in
        if property.isConstructorArgument {
            return "\(property.name): \(property.name)"
        }
        if isEnumType(property.type, enumName: enumName) {
            return "\(property.name): module"
        }
        if let qualifier = property.namedQualifier, qualifier.hasSuffix(".allCases") {
            return "\(property.name): resolver.resolve(\(property.type).self, name: module.rawValue)!"
        } else if let qualifier = property.namedQualifier {
            return "\(property.name): resolver.resolve(\(property.type).self, name: \(normalizeNameExpression(qualifier)))!"
        }
        return "\(property.name): resolver~>"
    }

    let body = buildRegistrationBody(
        registerPrefix: "self.register(\(registrationTarget).self, name: module.rawValue)",
        className: className,
        arguments: arguments,
        scopeText: scopeText,
        constructorArgumentName: constructorArgumentName
    )
    return "\(enumName).allCases.forEach { module in\n\t\t\t\(body)\n\t\t}"
}

func buildClassLevelAllCasesRegistration(
    className: String,
    scopeText: String,
    interfaceName: String?,
    properties: [StoredPropertyDIInfo],
    enumName: String
) -> String {
    let registrationTarget = interfaceName ?? className
    let constructorArgumentName = properties.first(where: { $0.isConstructorArgument })?.name
    let arguments = properties.map { property in
        if property.isConstructorArgument {
            return "\(property.name): \(property.name)"
        }
        if isEnumType(property.type, enumName: enumName) {
            return "\(property.name): module"
        }
        if property.namedQualifier != nil {
            return "\(property.name): resolver.resolve(\(property.type).self, name: module.rawValue)!"
        }
        return "\(property.name): resolver~>"
    }

    let body = buildRegistrationBody(
        registerPrefix: "self.register(\(registrationTarget).self, name: module.rawValue)",
        className: className,
        arguments: arguments,
        scopeText: scopeText,
        constructorArgumentName: constructorArgumentName
    )
    return "\(enumName).allCases.forEach { module in\n\t\t\t\(body)\n\t\t}"
}

func buildClassLevelSingleNameRegistration(
    className: String,
    scopeText: String,
    interfaceName: String?,
    properties: [StoredPropertyDIInfo],
    nameExpression: String
) -> String {
    let registrationTarget = interfaceName ?? className
    let nameValue = normalizeNameExpression(nameExpression)
    let constructorArgumentName = properties.first(where: { $0.isConstructorArgument })?.name
    let arguments = properties.map { property in
        if property.isConstructorArgument {
            return "\(property.name): \(property.name)"
        }
        if property.namedQualifier != nil {
            return "\(property.name): resolver.resolve(\(property.type).self, name: \(nameValue))!"
        }
        return "\(property.name): resolver~>"
    }

    return buildRegistrationBody(
        registerPrefix: "self.register(\(registrationTarget).self)",
        className: className,
        arguments: arguments,
        scopeText: scopeText,
        constructorArgumentName: constructorArgumentName
    )
}
