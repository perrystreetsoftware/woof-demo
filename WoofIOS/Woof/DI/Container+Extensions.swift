import DataSource
import PresentationAccount
import PresentationFavorites
import PresentationBrowse
import PresentationHome
import PresentationNavigation
import PresentationProfile
import Repositories
import Swinject
import UseCase
import Utils

extension Container {
    func injectEverythingForProduction() -> Container {
        self
            .injectUtilsGenerated()
            .injectDataSourceGenerated()
            .injectRepositoriesGenerated()
            .injectUseCaseGenerated()
            .injectPresentationNavigationGenerated()
            .injectPresentationBrowseGenerated()
            .injectPresentationProfileGenerated()
            .injectPresentationFavoritesGenerated()
            .injectPresentationAccountGenerated()
            .injectPresentationHomeGenerated()
            .injectRouterEntries()
    }

    private func injectRouterEntries() -> Container {
        register([RouterEntryImplementing].self) { resolver in
            [
                resolver.resolve(HomeRouterEntry.self)!,
                resolver.resolve(ProfileRouterEntry.self)!,
            ]
        }
        return self
    }
}
