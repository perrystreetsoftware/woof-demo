import Foundation

func generateAutoregisters(in swiftFiles: [String]) -> GenerationResult {
    var autoregisters: [String] = []
    var regexes: [(NSRegularExpression, String)] = []
    var storedPropertiesCache: [String: [StoredPropertyDIInfo]] = [:]
    var neededImports: Set<String> = []

    let classLevelParamRegex = try! NSRegularExpression(pattern: classLevelParamPattern, options: [])
    var classLevelParamMap: [String: String] = [:]

    for file in swiftFiles {
        guard let content = try? String(contentsOfFile: file, encoding: .utf8) else {
            print("⚠️ Skipping unreadable file: \(file)")
            continue
        }
        let scanMatches = classLevelParamRegex.matches(in: content, options: [], range: NSRange(location: 0, length: content.utf16.count))
        for scanMatch in scanMatches {
            if let paramRange = Range(scanMatch.range(at: 1), in: content),
                let classRange = Range(scanMatch.range(at: 2), in: content)
            {
                let param = String(content[paramRange]).trimmingCharacters(in: .whitespaces)
                let className = String(content[classRange])
                classLevelParamMap[className] = param
            }
        }
    }

    for (pattern, scope) in annotationPatterns {
        let regex = try! NSRegularExpression(pattern: pattern, options: [])
        regexes.append((regex, scope))
    }

    let factoryCollectionRegex = try! NSRegularExpression(pattern: factoryCollectionClassPattern, options: [])
    var collections: [String: [String]] = [:]

    for file in swiftFiles {
        guard let content = try? String(contentsOfFile: file, encoding: .utf8) else {
            print("⚠️ Skipping unreadable file: \(file)")
            continue
        }

        for (regex, scope) in regexes {
            let matches = regex.matches(in: content, options: [], range: NSRange(location: 0, length: content.utf16.count))
            for match in matches {
                if let range = Range(match.range(at: 1), in: content) {
                    let className = String(content[range])
                    let scopeText = ".\(scope)"

                    let annotationType: AnnotationType? = {
                        if let fullMatchRange = Range(match.range(at: 0), in: content) {
                            let fullMatch = String(content[fullMatchRange])
                            return AnnotationType(from: fullMatch.firstWord!)
                        } else {
                            return nil
                        }
                    }()

                    let storedProperties: [StoredPropertyDIInfo] = {
                        let cacheKey = "\(file)::\(className)"

                        if let cachedProperties = storedPropertiesCache[cacheKey] {
                            return cachedProperties
                        }

                        let parsedProperties: [StoredPropertyDIInfo]
                        if let classBody = extractClassBody(from: content, className: className) {
                            parsedProperties = parseStoredProperties(for: classBody)
                        } else {
                            parsedProperties = []
                        }

                        storedPropertiesCache[cacheKey] = parsedProperties
                        return parsedProperties
                    }()

                    let hasNamedDependency = storedProperties.contains { $0.namedQualifier != nil }
                    let constructorArgumentProperties = storedProperties.filter { $0.isConstructorArgument }
                    let hasConstructorArgument = constructorArgumentProperties.isEmpty == false

                    if constructorArgumentProperties.count > 1 {
                        fatalError("@DIArgument supports only one property per class. Found \(constructorArgumentProperties.count) in \(className).")
                    }

                    if hasNamedDependency || hasConstructorArgument || classLevelParamMap[className] != nil {
                        for line in content.components(separatedBy: .newlines) {
                            let trimmed = line.trimmingCharacters(in: .whitespaces)
                            if trimmed.hasPrefix("import ") && !trimmed.contains("@") {
                                let moduleName = trimmed.replacingOccurrences(of: "import ", with: "").trimmingCharacters(in: .whitespaces)
                                if !moduleName.isEmpty && moduleName != "Foundation" && moduleName != "Combine" && moduleName != "SwiftUI" {
                                    neededImports.insert(moduleName)
                                }
                            }
                        }
                    }

                    let classLevelParam: String? = classLevelParamMap[className]
                    let isClassLevelAllCases = classLevelParam?.hasSuffix(".allCases") == true
                    let isClassLevelSingleName = classLevelParam != nil && !isClassLevelAllCases

                    let classLevelAllCasesEnum: String? = {
                        if isClassLevelAllCases, let param = classLevelParam {
                            return extractAllCasesEnumName(from: param)
                        }
                        return nil
                    }()

                    let propertyLevelAllCases: String? = storedProperties.compactMap { prop -> String? in
                        guard let qualifier = prop.namedQualifier else { return nil }
                        return extractAllCasesEnumName(from: qualifier)
                    }.first

                    let allCasesEnumName = classLevelAllCasesEnum ?? propertyLevelAllCases
                    let hasAllCasesDependency = allCasesEnumName != nil

                    let interfaceName: String? = {
                        if match.numberOfRanges > 2,
                            let interfaceRange = Range(match.range(at: 2), in: content)
                        {
                            return String(String(content[interfaceRange]).dropFirst(2))
                        }
                        return nil
                    }()

                    let logSuffix = interfaceName.map { " against interface \($0)" } ?? ""

                    if isClassLevelSingleName, let nameExpr = classLevelParam {
                        autoregisters.append(
                            buildClassLevelSingleNameRegistration(
                                className: className,
                                scopeText: scopeText,
                                interfaceName: interfaceName,
                                properties: storedProperties,
                                nameExpression: nameExpr
                            )
                        )
                        neededImports.insert("DomainModels")
                        print("Registering \(className) in \(scope) scope\(logSuffix) using class-level single name \(nameExpr)")
                    } else if isClassLevelAllCases, let enumName = allCasesEnumName {
                        autoregisters.append(
                            buildClassLevelAllCasesRegistration(
                                className: className,
                                scopeText: scopeText,
                                interfaceName: interfaceName,
                                properties: storedProperties,
                                enumName: enumName
                            )
                        )
                        neededImports.insert("DomainModels")
                        print("Registering \(className) in \(scope) scope\(logSuffix) using class-level allCases forEach for \(enumName)")
                    } else if hasAllCasesDependency, let enumName = allCasesEnumName {
                        autoregisters.append(
                            buildAllCasesRegistration(
                                className: className,
                                scopeText: scopeText,
                                interfaceName: interfaceName,
                                properties: storedProperties,
                                enumName: enumName
                            )
                        )
                        neededImports.insert("DomainModels")
                        print("Registering \(className) in \(scope) scope\(logSuffix) using allCases forEach for \(enumName)")
                    } else if hasNamedDependency {
                        autoregisters.append(
                            buildCustomRegistration(
                                className: className,
                                registrationTypeName: className,
                                scopeText: scopeText,
                                interfaceName: interfaceName,
                                properties: storedProperties
                            )
                        )
                        print("Registering \(className) in \(scope) scope\(logSuffix) using custom named resolution")
                    } else if hasConstructorArgument,
                        let constructorArgumentType = constructorArgumentProperties.first?.type
                    {
                        autoregisters.append(
                            buildConstructorArgumentAutoregistration(
                                className: className,
                                registrationTypeName: className,
                                scopeText: scopeText,
                                interfaceName: interfaceName,
                                constructorArgumentType: constructorArgumentType
                            )
                        )
                        print("Auto registering \(className) in \(scope) scope\(logSuffix) with @DIArgument")
                    } else if let iface = interfaceName, annotationType?.isForProtocol == true {
                        autoregisters.append("self.autoregister(\(iface).self, initializer: \(className).init).inObjectScope(\(scopeText))")
                        print("Auto registering \(className) in \(scope) scope against interface \(iface)")
                    } else if let iface = interfaceName, annotationType == .mockApi {
                        autoregisters.append("self.pss_registerMock(\(iface).self, \(className).self, \(className).init)")
                        print("Auto registering mock \(className) in \(scope) scope against interface \(iface)")
                    } else if interfaceName != nil {
                        assertionFailure()
                    } else {
                        autoregisters.append("self.autoregister(\(className).self, initializer: \(className).init).inObjectScope(\(scopeText))")
                        print("Auto registering \(className) in \(scope) scope")
                    }
                }
            }
        }

        let factoryCollectionMatches = factoryCollectionRegex.matches(
            in: content,
            options: [],
            range: NSRange(location: 0, length: content.utf16.count)
        )
        for match in factoryCollectionMatches {
            guard let classRange = Range(match.range(at: 1), in: content) else {
                continue
            }

            let className = String(content[classRange])

            guard let conformancesRange = Range(match.range(at: 2), in: content) else {
                fatalError("@FactoryCollection class \(className) in \(file) must conform to at least one protocol.")
            }

            let conformances = String(content[conformancesRange])
                .split(separator: ",")
                .map { $0.trimmingCharacters(in: .whitespacesAndNewlines) }

            guard let firstConformance = conformances.first, firstConformance.isEmpty == false else {
                fatalError("@FactoryCollection class \(className) in \(file) must conform to at least one protocol.")
            }

            let protocolName = firstConformance.replacingOccurrences(of: "any ", with: "")

            collections[protocolName, default: []].append(className)
            autoregisters.append("self.autoregister(\(className).self, initializer: \(className).init).inObjectScope(.transient)")
            print("Auto registering \(className) in transient scope (FactoryCollection for \(protocolName))")
        }
    }

    for (protocolName, classNames) in collections.sorted(by: { $0.key < $1.key }) {
        let resolveLines = classNames.sorted().map { "\t\t\t\tr.resolve(\($0).self)!" }.joined(separator: ",\n")
        let collectionRegistration = "self.register([\(protocolName)].self) { r in\n\t\t\treturn [\n\(resolveLines)\n\t\t\t]\n\t\t}"
        autoregisters.append(collectionRegistration)
        print("Auto registering collection for \(protocolName) with \(classNames.count) implementations")
    }

    let autoregisterLines = autoregisters.filter { $0.hasPrefix("self.autoregister(") || $0.hasPrefix("self.pss_registerMock(") }
    let customRegisterLines = autoregisters.filter { !$0.hasPrefix("self.autoregister(") && !$0.hasPrefix("self.pss_registerMock(") }

    let sortedRegistrations = (autoregisterLines.sorted() + customRegisterLines.sorted()).joined(separator: "\n\t\t")

    return GenerationResult(
        registrations: sortedRegistrations,
        additionalImports: neededImports
    )
}

func createContainerExtension(for packageName: String, autoRegisters: String, additionalImports: [String]) -> String {
    var additionalImportsContent = ""
    additionalImports.forEach { newImport in
        additionalImportsContent += "\nimport \(newImport)"
    }

    return """
        import Swinject
        import SwinjectAutoregistration\(additionalImportsContent)

        public extension Container {
            func inject\(packageName)Generated() -> Container {
                \(autoRegisters)
                return self
            }
        }

        """
}
