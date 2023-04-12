//
//  MoviesViewModel.swift
//  iosApp
//
//  Created by Azzam Habib on 2/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MoviesViewModel: ObservableObject {
    var moviesRepository: MoviesRepository?
    
    @Published private (set) var loading: Bool = false
    @Published private (set) var moviesList = [Movie]()
    @Published private (set) var searchMoviesList = [Movie]()
    @Published private (set) var moviesError: NetworkException? = nil
    @Published var showErrorDialog: Bool = false
    @Published var searchedText = ""
    
    
    
    init(moviesRepository: MoviesRepository?) {
        self.moviesRepository = moviesRepository
    }
    
    func setMoviesRepository(moviesRepository: MoviesRepository){
        self.moviesRepository = moviesRepository
    }
    
    func getMovies(){
        self.loading = true
        
        moviesRepository?.getMovies(completionHandler: { (response, error) in
            self.loading = false
            switch response {
            case is ResultSuccess<NSArray>:
                print("Success")
                var moviesFromApi = (response)?.value as? [Movie]

                self.moviesRepository?.getFavoriteMovies(completionHandler: { (moviesFromDB, dbError) in
                
                    moviesFromDB?.forEach({ movieDB in
                        guard let index = moviesFromApi?.firstIndex(where: { item in
                            item.id == movieDB.id
                        }) else { return }
                      
                        if(index >= 0){
                            guard let temp: Movie = moviesFromApi?[index] else { return }
                                temp.isFavorite = true
                                moviesFromApi?[index] = temp
                                print(temp)
                        }
                    })
                })
                self.moviesList = moviesFromApi ?? []
                break
            case is ResultError<NSArray>:
                print("ResultError")
                print(response?.code ?? "NO ERROR CODE")
                print(response?.errorBody ?? "NO ERROR BODY")
                
                self.moviesError = NetworkException(code: response?.code, errorBody: response?.errorBody)
                self.showErrorDialog = true
                break
            case .none:
                print("None Called")
                break
                
            case .some(_):
                print("Some Called")
                break
                
            }
        })
    }
    
    func addToFavourite(movie: Movie){
        print("add movie to favourite")
        print(movie)
        moviesRepository?.insertMovie(movie: movie, completionHandler: { (_, _) in
            
        })
    }
    
    func getMoviesFromDB(){
        moviesRepository?.getFavoriteMovies(completionHandler: { (movies, error) in
            self.moviesList = movies ?? []
        })
    }
   
    func searchForMovie(expression: String){
        self.loading = true
        moviesRepository?.searchForMovie(expression: expression, completionHandler: { (response, error) in
            self.loading = false
            switch response {
            case is ResultSuccess<NSArray>:
                var moviesFromApi = response?.value as? [Movie]
                self.moviesRepository?.getFavoriteMovies(completionHandler: { (moviesFromDB, dbError) in
                
                    moviesFromDB?.forEach({ movieDB in
                        guard let index = moviesFromApi?.firstIndex(where: { item in
                            item.id == movieDB.id
                        }) else { return }
                      
                        if(index >= 0){
                            guard let temp: Movie = moviesFromApi?[index] else { return }
                                temp.isFavorite = true
                                moviesFromApi?[index] = temp
                                print(temp)
                        }
                    })
                })

                self.searchMoviesList = moviesFromApi ?? []
                break;
            case is ResultError<NSArray>:
                print("ResultError")
                print(response?.code ?? "NO ERROR CODE")
                print(response?.errorBody ?? "NO ERROR BODY")
                
                self.moviesError = NetworkException(code: response?.code, errorBody: response?.errorBody)
                self.showErrorDialog = true
                break
            case .some:
            break
            case .none:
                break
            }
        })
    }
}
