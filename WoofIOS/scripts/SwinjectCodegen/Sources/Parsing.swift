import Foundation

extension String {
    var firstWord: String? {
        let pattern = #"^\s*([^\s]+)"#
        let regex = try? NSRegularExpression(pattern: pattern, options: [.anchorsMatchLines])

        if let match = regex?.firstMatch(in: self, options: [], range: NSRange(self.startIndex..., in: self)),
            let range = Range(match.range(at: 1), in: self)
        {
            return String(self[range])
        }
        return nil
    }
}

func extractClassBody(from content: String, className: String) -> String? {
    let classPattern = "class\\s+\(NSRegularExpression.escapedPattern(for: className))\\b[^\\{]*\\{"
    guard let classRegex = try? NSRegularExpression(pattern: classPattern, options: []) else {
        return nil
    }

    let searchRange = NSRange(content.startIndex..., in: content)
    guard
        let classMatch = classRegex.firstMatch(in: content, options: [], range: searchRange),
        let matchRange = Range(classMatch.range(at: 0), in: content),
        let openingBrace = content[matchRange].lastIndex(of: "{")
    else {
        return nil
    }

    var depth = 0
    var closingBrace: String.Index?
    var index = openingBrace

    while index < content.endIndex {
        let character = content[index]
        if character == "{" {
            depth += 1
        } else if character == "}" {
            depth -= 1
            if depth == 0 {
                closingBrace = index
                break
            }
        }
        index = content.index(after: index)
    }

    guard let foundClosingBrace = closingBrace else {
        return nil
    }

    let bodyStart = content.index(after: openingBrace)
    let bodyRange = bodyStart..<foundClosingBrace
    return String(content[bodyRange])
}

private let diNamedInheritedSentinel = "__inherited__"

private enum DIArgumentParseResult {
    case marker
    case invalidArguments
}

func parseDINamedQualifier(from line: String) -> String? {
    if let regex = try? NSRegularExpression(pattern: "@DINamed\\s*\\((.+)\\)", options: []) {
        let range = NSRange(line.startIndex..., in: line)
        if let match = regex.firstMatch(in: line, options: [], range: range),
            let qualifierRange = Range(match.range(at: 1), in: line)
        {
            return String(line[qualifierRange]).trimmingCharacters(in: .whitespacesAndNewlines)
        }
    }

    if let noArgsRegex = try? NSRegularExpression(pattern: "@DINamed\\s*(\\(\\s*\\))?(?:\\s+|\\s*$)", options: []) {
        let range = NSRange(line.startIndex..., in: line)
        if noArgsRegex.firstMatch(in: line, options: [], range: range) != nil {
            return diNamedInheritedSentinel
        }
    }

    return nil
}

private func parseDIArgument(from line: String) -> DIArgumentParseResult? {
    if let argsRegex = try? NSRegularExpression(pattern: "@DIArgument\\s*\\(([^)]*)\\)", options: []) {
        let range = NSRange(line.startIndex..., in: line)
        if let match = argsRegex.firstMatch(in: line, options: [], range: range),
            let argsRange = Range(match.range(at: 1), in: line)
        {
            let args = String(line[argsRange]).trimmingCharacters(in: .whitespacesAndNewlines)
            return args.isEmpty ? .marker : .invalidArguments
        }
    }

    if let markerRegex = try? NSRegularExpression(pattern: "@DIArgument(?:\\s+|\\s*$)", options: []) {
        let range = NSRange(line.startIndex..., in: line)
        if markerRegex.firstMatch(in: line, options: [], range: range) != nil {
            return .marker
        }
    }

    return nil
}

func parseStoredProperties(for classBody: String) -> [StoredPropertyDIInfo] {
    let propertyPattern = "^(?:[A-Za-z_][A-Za-z0-9_]*(?:\\([^\\)]*\\))?\\s+)*(var|let)\\s+([A-Za-z_][A-Za-z0-9_]*)\\s*:\\s*([^=\\{]+)"
    let propertyRegex = try! NSRegularExpression(pattern: propertyPattern, options: [])

    var properties: [StoredPropertyDIInfo] = []
    var pendingNamedQualifier: String?
    var pendingDIArgument = false
    var skipNextProperty = false

    for rawLine in classBody.components(separatedBy: .newlines) {
        let line = rawLine.trimmingCharacters(in: .whitespaces)
        var propertyLine = line

        if line.isEmpty {
            continue
        }

        if line.hasPrefix("//") || line.hasPrefix("/*") || line.hasPrefix("*") {
            continue
        }

        while propertyLine.hasPrefix("@") {
            if propertyLine.hasPrefix("@DINamed") {
                pendingNamedQualifier = parseDINamedQualifier(from: propertyLine)
                propertyLine = propertyLine.replacingOccurrences(
                    of: "@DINamed\\s*(?:\\([^)]*\\))?\\s*",
                    with: "",
                    options: .regularExpression
                ).trimmingCharacters(in: .whitespaces)
                if propertyLine.isEmpty || propertyLine.hasPrefix("@") {
                    continue
                }
            }

            if propertyLine.hasPrefix("@DIArgument") {
                guard let parseResult = parseDIArgument(from: propertyLine) else {
                    pendingNamedQualifier = nil
                    pendingDIArgument = false
                    skipNextProperty = false
                    break
                }

                switch parseResult {
                case .marker:
                    pendingDIArgument = true
                case .invalidArguments:
                    fatalError("@DIArgument only supports marker form (@DIArgument or @DIArgument())")
                }

                propertyLine = propertyLine.replacingOccurrences(
                    of: "@DIArgument\\s*(?:\\([^)]*\\))?\\s*",
                    with: "",
                    options: .regularExpression
                ).trimmingCharacters(in: .whitespaces)
                if propertyLine.isEmpty || propertyLine.hasPrefix("@") {
                    continue
                }
            }

            if propertyLine.hasPrefix("@Published") {
                skipNextProperty = true
                propertyLine = propertyLine.replacingOccurrences(
                    of: "@Published\\s*",
                    with: "",
                    options: .regularExpression
                ).trimmingCharacters(in: .whitespaces)
                if propertyLine.isEmpty || propertyLine.hasPrefix("@") {
                    break
                }
            }

            break
        }

        if line.hasPrefix("@Published") && propertyLine.isEmpty {
            continue
        }

        let range = NSRange(propertyLine.startIndex..., in: propertyLine)
        guard let match = propertyRegex.firstMatch(in: propertyLine, options: [], range: range) else {
            if line.hasPrefix("@") == false {
                pendingNamedQualifier = nil
                pendingDIArgument = false
                skipNextProperty = false
            }
            continue
        }

        guard
            let nameRange = Range(match.range(at: 2), in: propertyLine),
            let typeRange = Range(match.range(at: 3), in: propertyLine)
        else {
            pendingNamedQualifier = nil
            pendingDIArgument = false
            skipNextProperty = false
            continue
        }

        let name = String(propertyLine[nameRange])
        let rawType = String(propertyLine[typeRange]).trimmingCharacters(in: .whitespacesAndNewlines)
        let normalizedType = rawType.replacingOccurrences(of: "any ", with: "")

        if skipNextProperty {
            pendingNamedQualifier = nil
            pendingDIArgument = false
            skipNextProperty = false
            continue
        }

        if line.contains("lazy var") || propertyLine.contains("lazy var") {
            pendingNamedQualifier = nil
            pendingDIArgument = false
            continue
        }

        if propertyLine.contains("{") {
            pendingNamedQualifier = nil
            pendingDIArgument = false
            continue
        }

        if propertyLine.contains("=") {
            pendingNamedQualifier = nil
            pendingDIArgument = false
            continue
        }

        if primitiveTypes.contains(normalizedType) && pendingDIArgument == false {
            pendingNamedQualifier = nil
            pendingDIArgument = false
            continue
        }

        properties.append(
            StoredPropertyDIInfo(
                name: name,
                type: normalizedType,
                namedQualifier: pendingNamedQualifier,
                isConstructorArgument: pendingDIArgument
            )
        )

        pendingNamedQualifier = nil
        pendingDIArgument = false
        skipNextProperty = false
    }

    return properties
}
