# Woof

Woof is an open-source architecture demo app built by [Perry Street Software](https://www.perrystreet.com), the creators of [SCRUFF](https://www.scruff.com) and [Jack'd](https://www.jackd.com/). It mirrors the patterns we use in our production apps so we can showcase them in conference talks, blog posts, and engineering interviews without exposing proprietary code:

- MVVM and Clean Architecture with a strict, unidirectional flow of dependencies: View → ViewModel → UseCase → Repository → DataSource
- Atomic design system for the UI components, from primitives and tokens to organisms and templates
- Architectural lint rules written as unit tests
- BDD tests at the ViewModel layer, faking only the data source layer

The features are deliberately small. Woof is a "playdate" app for dogs: a grid of 1,000 dog profiles that you can browse, open fullscreen, swipe between, favorite, woof at, and message, plus a favorites and an account tab. All data is local. There are no network calls, no analytics, and no accounts. The dog names, breeds, neighborhoods, and bios are generated fixtures; the photos are generated illustrations bundled with each app.

The repository contains both the Android and iOS app, with strict feature parity and code parity between them.

| | Android | iOS |
|---|---|---|
| Folder | `WoofAndroid` | `WoofIOS` |
| UI | Jetpack Compose + Navigation 3 | SwiftUI + `NavigationStack` |
| Reactive framework | RxJava 3 | Combine |
| DI | Koin annotations | Swinject + macros + codegen |
| Tests | Kotest `BehaviorSpec` | Quick + Nimble with a Given / When / Then DSL |
| Linter | [Konsist](https://github.com/LemonAppDev/konsist) | [Harmonize](https://github.com/perrystreetsoftware/Harmonize) |

## Architecture

Both apps are using MVVM with a strict, unidirectional flow of dependencies:

```
View (Compose / SwiftUI)
  └─▶ ViewModel (one piece of state per ViewModel)
        └─▶ UseCase (single-purpose, one invoke() / callAsFunction())
              └─▶ Repository (owns in-memory state, maps DTO → domain)
                    └─▶ DataSource (interface / protocol + local implementation)
```

Every layer only talks to the layer directly below it. The rules that keep it that way are enforced with lint rules that run on every pull request.

### Modules / Packages

Each Android module has an equivalent Swift package with the same responsibility.

| Android module | Swift package | Responsibility |
|---|---|---|
| `app` | `Woof` (Xcode target) | Application entry point, DI startup, root view |
| `design-system` | `SwiftPackages/DesignSystem` | Atomic design system: `_Primitives`, `_Tokens`, `Atoms`, `Molecules`, `Organisms`, `Templates` |
| `resources` | `SwiftPackages/Resources` | Strings, icons, and bundled dog photos |
| `dto` | `SwiftPackages/DTO` | Data transfer objects, the shape of the data as a server would send it |
| `data/datasource` | `SwiftPackages/Data/DataSource` | Data source interfaces, their local implementations, the dog fixtures, and test fakes |
| `data/repositories` | `SwiftPackages/Data/Repositories` | Repositories and `DTOToDomain` mappers |
| `domain/model` | `SwiftPackages/Domain/Model` | Pure domain models and typed errors |
| `domain/usecase` | `SwiftPackages/Domain/UseCase` | Single-function use cases |
| `presentation/common` | `SwiftPackages/Presentation/Common` | ViewModel base classes, error adapter, image loading bridge |
| `presentation/navigation` | `SwiftPackages/Presentation/Navigation` | Destinations, back stack, navigator, router entries |
| `presentation/grid` | `SwiftPackages/Presentation/Grid` | The dog grid feature: `ViewModel`, `UIModel`, `Mapper`, `UI` |
| `presentation/profile` | `SwiftPackages/Presentation/Profile` | The fullscreen profile feature, built from several small ViewModels |
| `presentation/favorites` | `SwiftPackages/Presentation/Favorites` | The grid of favorited dogs |
| `presentation/account` | `SwiftPackages/Presentation/Account` | The read-only account screen for your own dog |
| `presentation/home` | `SwiftPackages/Presentation/Home` | The bottom navigation host for the Browse, Favorites, and Account tabs |
| `di` | `Woof/DI` + `SwiftPackages/Macros` | Koin module list on Android; DI macros and the container composition on iOS |
| `utils` | `SwiftPackages/Utils` | Schedulers, DI helpers, Combine extensions, Gherkin DSL |
| `testutils` | `SwiftPackages/TestUtils` | Test container composition, test scheduler, time advancing helpers |
| `konsist` | `SwiftPackages/HarmonizeRules` | Architectural lint rules |

### Key patterns

- **Adapter and Screen.** Adapters are the only views that own a ViewModel; they pass their state down to the Screen and forward the Screen's callbacks to the ViewModel's actions. Screens are pure functions of state that can be previewed without dependency injection.
- **Navigator.** Screens never navigate directly. Adapters or ViewModels use the `Navigator` to go to a `WoofDestination`, which pushes onto the shared `WoofBackStack` rendered by Navigation 3 on Android and `NavigationStack` on iOS.
- **Small ViewModels.** The profile screen is composed of six ViewModels (pager, header, details, woof, message, moderation). Each emits one piece of state and exposes `on*` actions.
- **UI models and mappers.** ViewModels never expose domain models to the view. `DomainToUIModel` mappers produce UI models, while resource mapping lives in extensions in the screens layer.
- **Reactive programming.** Repositories expose streams backed by subjects (`BehaviorSubject` / `CurrentValueSubject`), use cases compose them, and the ViewModels map them into UI state.
- **DTOs and mappers.** Data sources return DTOs and repositories transform them into domain models using `DTOToDomain` mappers.
- **Atomic design.** Screens only use `Template*`, `Org*`, `Mol*`, and `Atom*` components. Colors, spacing, sizing, and typography are only reachable through theme tokens inside the design system.
- **Dependency injection using annotations.** Android uses Koin's `@Single`, `@Factory`, and `@KoinViewModel`. iOS has the same `@Single` and `@Factory` annotations, implemented as Swift macros, and `scripts/SwinjectCodegen` generates the Swinject registrations from them.

### Platform-specific choices

- **Errors.** Android ViewModels emit `Throwable`s. iOS ViewModels emit typed error enums (`WoofError`, `MessageError`, ...) defined next to the domain models.
- **Interfaces.** Android interfaces start with `I*` (`IDogsDataSource`). iOS protocols end with `*Implementing` (`DogsDataSourceImplementing`).

### Testing

Tests live only in the ViewModel layer and are written in a BDD style (`Given` / `When` / `Then`). The ViewModel test suites cover the real use cases and repositories as well; only the data source layer is replaced with fakes (no mocking libraries). Data source factories such as `DogsDataSourceFactory().withDogs(450)` configure the fakes.

```bash
cd WoofAndroid && ./gradlew runUnitTests
```

```bash
cd WoofIOS && bash scripts/run-unit-tests.sh
```

### Lint rules

Architectural rules are BDD specs in the `konsist` module (Android) and the `HarmonizeRules` package (iOS). Every rule contains a `LintRuleMessage` explaining what it checks, why the rule exists, how to fix a violation, and a bad/good example.

```bash
cd WoofAndroid && ./gradlew runKonsistTests
```

```bash
cd WoofIOS && bash scripts/run-harmonize.sh
```

## Building

### Android

Open `WoofAndroid` in Android Studio, or run:

```bash
cd WoofAndroid
./gradlew :app:installDebug
```

Requires JDK 21.

### iOS

Open `WoofIOS/Woof.xcodeproj` in Xcode and run the `Woof` scheme, or build from the command line:

```bash
cd WoofIOS
xcodebuild build -project Woof.xcodeproj -scheme Woof -destination 'platform=iOS Simulator,name=iPhone 17 Pro Max'
```
