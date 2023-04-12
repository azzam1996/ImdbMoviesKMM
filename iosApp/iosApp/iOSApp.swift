import SwiftUI
import shared

@main
struct iOSApp: App {
    let moviesRepository = RepositoryModule().moviesRepository
	var body: some Scene {
		WindowGroup {
            BottomNavigationBar(moviesRepository: moviesRepository)
		}
	}
}
