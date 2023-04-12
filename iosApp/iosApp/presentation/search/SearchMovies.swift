//
//  SearchMovies.swift
//  iosApp
//
//  Created by Azzam Habib on 2/19/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchMovies: View {
    private var moviesRepository: MoviesRepository
    @StateObject var moviesViewModel = MoviesViewModel(moviesRepository: nil)
    @State private var showDialog: Bool = false
    @State private var displayMovie: Movie? = nil
    @State var searchText: String = ""
    
    
    init(moviesRepository: MoviesRepository){
        self.moviesRepository = moviesRepository
    }
    
    var body: some View {
            ZStack{
                Image(uiImage: UIImage(named: "blue_back")!)
                        .resizable()
                        .edgesIgnoringSafeArea(.all)
                        .frame(width: UIScreen.main.bounds.width, height: UIScreen.main.bounds.height)
                if(moviesViewModel.loading){
                    HStack(alignment:.center){
                        ProgressView()
                            .scaleEffect(2)
                            .frame(width: 100,height: 100)
                    }
                    .frame(
                          minWidth: 0,
                          maxWidth: .infinity,
                          minHeight: 0,
                          maxHeight: .infinity,
                          alignment: .center
                        )
                }
                else{
                    VStack{
                        HStack{
                            Button {
                                self.moviesViewModel.searchForMovie(expression: searchText)
                            } label: {
                                Image(systemName: "magnifyingglass")
                                    .resizable()
                                    .foregroundColor(Color.gray)
                                    .frame(width: 20,height: 20)
                            }
                            
                            TextField("Search ...",text: self.$searchText)
                                .padding(.leading, 20)
                        }
                        .padding(EdgeInsets.init(top: 100, leading: 15, bottom: 15, trailing: 15))
                        ScrollView{
                            LazyVStack{
                                ForEach(moviesViewModel.searchMoviesList, id: \.self.id){ movie in
                                    MovieItemUI(movie: movie,onAddToFavouriteClicked:{ param in
                                        moviesViewModel.addToFavourite(movie: param)
                                    }) { movie in
                                        displayMovie = movie
                                        showDialog = true
                                    }
                                }
                            }
                        }
                        .padding(EdgeInsets.init(top: 30, leading: 15, bottom: 75, trailing: 15))
                    }
                }
            }
            .onAppear(){
                moviesViewModel.setMoviesRepository(moviesRepository: self.moviesRepository)
            }
            .customDialog(isShowing: showDialog) { 
                if(displayMovie != nil){
                    DisplayMovieUI(movie: displayMovie!,isShowing: $showDialog)
                }
            }
            .customDialog(isShowing: self.moviesViewModel.showErrorDialog) {
                VStack {
                    Text(String(describing: self.moviesViewModel.moviesError?.code))
                    .fontWeight(.bold)
                  Divider()
                    Text((self.moviesViewModel.moviesError?.errorBody ?? "NO Error "))
                    .padding(.bottom, 10)
                  Button(action: {
                    self.moviesViewModel.showErrorDialog = false
                  }) {
                    Text("Close dialog")
                      .autocapitalization(.allCharacters)
                      .frame(minWidth: 0, maxWidth: .infinity)
                      .padding()
                  }
                }
                .padding()
                .background(
                    RoundedRectangle(cornerRadius: 20)
                        .foregroundColor(Color.white)
                )
            }
        }
    }


struct SearchMovies_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
