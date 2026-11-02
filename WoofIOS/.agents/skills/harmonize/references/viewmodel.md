# viewmodel rules

Rules for ViewModels and their state. Read before you create or change a ViewModel.

- [Presentation code receives domain models from use cases; only previews construct them.](viewmodel/presentation-does-not-build-domain-models.md)
- [ViewModel actions are named on<Something><Verb>, never in the past tense.](viewmodel/view-model-actions-use-present-tense.md)
- [ViewModels must not import repositories or data sources.](viewmodel/view-models-do-not-depend-on-repositories.md)
- [ViewModels, UI models, and mappers must not import SwiftUI, design system, or resource types.](viewmodel/view-models-do-not-import-swift-ui.md)
- [ViewModels must not start subscriptions in init.](viewmodel/view-models-do-not-subscribe-in-initializers.md)
- [ViewModels must extend StateProducingViewModel, StateDerivingViewModel, or StatelessViewModel.](viewmodel/view-models-inherit-from-base-view-models.md)
- [ViewModels may only inject UseCases, Mappers, NavigatorImplementing (for state-driven navigation), and @DIArgument values.](viewmodel/view-models-inject-only-use-cases-and-mappers.md)
- [ViewModels live in Presentation/<Feature>/Sources/<Target>/ViewModel.](viewmodel/view-models-live-in-view-model-package.md)
- [ViewModels must be annotated with @Factory.](viewmodel/view-models-use-factory-annotation.md)

