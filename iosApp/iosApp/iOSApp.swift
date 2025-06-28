import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        CommonModuleKt.initializeKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
