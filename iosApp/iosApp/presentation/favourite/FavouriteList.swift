//
//  FavouriteList.swift
//  iosApp
//
//  Created by Azzam Habib on 2/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FavouriteList: View {
    
    private var moviesRepository: MoviesRepository
    @StateObject var moviesViewModel = MoviesViewModel(moviesRepository: nil)
    @State private var showDialog = false
    @State private var displayMovie: Movie? = nil
    
    init(moviesRepository: MoviesRepository){
        self.moviesRepository = moviesRepository
    }
    var body: some View {
        ZStack{
            Image(uiImage: UIImage(named: "blue_back")!)
                    .resizable()
                    .edgesIgnoringSafeArea(.all)
                    .frame(width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.height)
            
            ScrollView{
                LazyVStack{
                    ForEach(moviesViewModel.moviesList, id: \.self.id){ movie in
                        MovieItemUI(movie: movie,onAddToFavouriteClicked:{ param in
                            moviesViewModel.addToFavourite(movie: param)
                        }) { movie in
                            displayMovie = movie
                            showDialog = true
                        }
                       
                    }
                }
            }
            .padding(EdgeInsets.init(top: 100, leading: 15, bottom: 75, trailing: 15))
        }
        .onAppear(){
            moviesViewModel.setMoviesRepository(moviesRepository: self.moviesRepository)
            moviesViewModel.getMoviesFromDB()
        }
        .customDialog(isShowing: showDialog) {
            if(displayMovie != nil){
                DisplayMovieUI(movie: displayMovie!,isShowing: $showDialog)
            }
            
        }
    }
}

struct FavouriteList_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
