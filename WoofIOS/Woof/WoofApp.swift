import SwiftUI
import Swinject
import Utils

@main
struct WoofApp: App {
    init() {
        InjectSettings.resolver = Container().injectEverythingForProduction().synchronize()
    }

    var body: some Scene {
        WindowGroup {
            WoofRootView()
        }
    }
}
