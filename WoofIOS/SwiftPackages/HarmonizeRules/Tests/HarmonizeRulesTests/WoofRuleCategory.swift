import Harmonize

/// The layers of WoofIOS. One case per rule folder. The generated `harmonize`
/// skill shows one reference file per case.
enum WoofRuleCategory: String, RuleCategory {
    case di
    case dataSource = "datasource"
    case designSystem = "designsystem"
    case mappers
    case meta
    case models
    case repositories
    case tests
    case ui
    case useCase = "usecase"
    case viewModel = "viewmodel"

    var categoryName: String { rawValue }

    var categoryDescription: String {
        switch self {
        case .di: "Rules for dependency injection: module registration and injected types. Read before you register or inject a dependency."
        case .dataSource: "Rules for data sources and their interfaces. Read before you create or change a data source."
        case .designSystem: "Rules for the design system: atoms, molecules, organisms, templates, roles, and tokens. Read before you create or change a UI component."
        case .mappers: "Rules for mappers between DTOs, domain models, and UI models. Read before you create or change a mapper."
        case .meta: "Rules about the lint rules themselves. Read before you write a lint rule."
        case .models: "Rules for DTOs, domain models, and state types. Read before you create or change a model."
        case .repositories: "Rules for repositories. Read before you create or change a repository."
        case .tests: "Rules for unit tests. Read before you write a test."
        case .ui: "Rules for screens, adapters, and view modifiers. Read before you create or change a view."
        case .useCase: "Rules for use cases. Read before you create or change a use case."
        case .viewModel: "Rules for ViewModels and their state. Read before you create or change a ViewModel."
        }
    }
}
