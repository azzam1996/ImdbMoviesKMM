//
//  BottomNavigationBar.swift
//  iosApp
//
//  Created by Azzam Habib on 2/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BottomNavigationBar: View {
    private var moviesRepository: MoviesRepository
    @State private var selection = 0

    init(moviesRepository: MoviesRepository){
        self.moviesRepository = moviesRepository
    }
    var body: some View {
        TabView(selection: $selection) {
            MoviesList(moviesRepository: moviesRepository)
                .tabItem {
                    Image(systemName: "play.fill")
                    Text("Movies")
            }
            FavouriteList(moviesRepository: moviesRepository)
                .tabItem {
                    Image(systemName: "heart.fill")
                    Text("Favourite")
            }
            SearchMovies(moviesRepository: moviesRepository)
                .tabItem {
                    Image(systemName: "magnifyingglass")
                    Text("Search")
            }
        }
    }
}

struct BottomNavigationBar_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
