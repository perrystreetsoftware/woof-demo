import Foundation

extension Array where Element: Hashable {
    func uniqued() -> [Element] {
        var seen = Set<Element>()
        return filter { seen.insert($0).inserted }
    }
}

enum AnnotationType: String {
    case factoryForProtocol = "@FactoryForProtocol"
    case singleForProtocol = "@SingleForProtocol"
    case factory = "@Factory"
    case weakFactory = "@WeakFactory"
    case single = "@Single"
    case mockApi = "@MockApi"

    init?(from string: String) {
        let stripped =
            string.contains("(")
            ? String(string.prefix(while: { $0 != "(" }))
            : string
        self.init(rawValue: stripped)
    }

    var isForProtocol: Bool {
        return self == .factoryForProtocol || self == .singleForProtocol
    }
}

struct StoredPropertyDIInfo {
    let name: String
    let type: String
    let namedQualifier: String?
    let isConstructorArgument: Bool
}

struct GenerationResult {
    let registrations: String
    let additionalImports: Set<String>
}

let primitiveTypes = Set(["Int", "Bool", "String", "Float", "Double"])

let annotationPatterns: [(String, String)] = [
    ("@MockApi\\s+public\\s+final\\s+class\\s+(\\w+)(: \\w+)", "transient"),
    ("@MockApi\\s+final\\s+public\\s+class\\s+(\\w+)(: \\w+)", "transient"),
    ("@SingleForProtocol\\s+public\\s+final\\s+class\\s+(\\w+)(: \\w+)", "container"),
    ("@SingleForProtocol\\s+final\\s+public\\s+class\\s+(\\w+)(: \\w+)", "container"),
    ("@FactoryForProtocol\\s+public\\s+final\\s+class\\s+(\\w+)(: \\w+)", "transient"),
    ("@FactoryForProtocol\\s+final\\s+public\\s+class\\s+(\\w+)(: \\w+)", "transient"),
    ("@Factory\\([^)]+\\)\\s+public\\s+final\\s+class\\s+(\\w+)", "transient"),
    ("@Factory\\([^)]+\\)\\s+final\\s+public\\s+class\\s+(\\w+)", "transient"),
    ("@Factory\\s+public\\s+final\\s+class\\s+(\\w+)", "transient"),
    ("@Factory\\s+final\\s+public\\s+class\\s+(\\w+)", "transient"),
    ("@WeakFactory\\s+public\\s+final\\s+class\\s+(\\w+)", "weak"),
    ("@WeakFactory\\s+internal\\s+final\\s+class\\s+(\\w+)", "weak"),
    ("@WeakFactory\\s+final\\s+public\\s+class\\s+(\\w+)", "weak"),
    ("@Single\\([^)]+\\)\\s+public\\s+final\\s+class\\s+(\\w+)", "container"),
    ("@Single\\([^)]+\\)\\s+final\\s+public\\s+class\\s+(\\w+)", "container"),
    ("@Single\\s+public\\s+final\\s+class\\s+(\\w+)", "container"),
    ("@Single\\s+final\\s+public\\s+class\\s+(\\w+)", "container"),
]

let factoryCollectionClassPattern =
    "@FactoryCollection\\s+(?:public\\s+final\\s+class|final\\s+public\\s+class)\\s+(\\w+)(?:\\s*:\\s*([^\\{\\n]+))?"

let classLevelParamPattern =
    "@(?:Factory|Single)\\(([^)]+)\\)\\s+(?:public\\s+final|final\\s+public)\\s+class\\s+(\\w+)"
