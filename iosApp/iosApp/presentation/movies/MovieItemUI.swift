//
//  MovieItemUI.swift
//  iosApp
//
//  Created by Azzam Habib on 2/5/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Kingfisher

struct MovieItemUI: View {
    let movie: Movie
    var onAddToFavouriteClicked: (Movie) -> Void
    var onShowMovieClicked: (Movie) -> Void
    @State var isFavorite: Bool = false
    
    init(movie: Movie, onAddToFavouriteClicked: @escaping (Movie) -> Void, onShowMovieClicked: @escaping (Movie) -> Void) {
        self.movie = movie
        self.onAddToFavouriteClicked = onAddToFavouriteClicked
        self.onShowMovieClicked = onShowMovieClicked
        self.isFavorite = movie.isFavorite
    }
    var body: some View {
        Button (action: {
            self.onShowMovieClicked(movie)
        }){
            HStack{
                KFImage(URL(string: movie.image ?? "placeholder"))
                    .placeholder{
                        GrayCircle()
                    }
                    .resizable()
                    .clipShape(Circle())
                    .frame(width: 100, height: 100)
                VStack(alignment: .leading){
                    Text(movie.title ?? "")
                        .font(.custom("sans-serif", size: 20))
                        .fontWeight(.bold)
                        .padding(.leading, 15)
                        .padding(.top, 20)
                        .buttonStyle(PlainButtonStyle())
                    
                    Text(movie.imDbRating ?? "")
                        .font(.custom("sans-serif", size: 15))
                        .fontWeight(.light)
                        .padding(.top, 2)
                        .padding(.leading, 15)
                    HStack{
                        Spacer()
                        Button {
                            self.onAddToFavouriteClicked(movie)
                            self.isFavorite = true
                        } label: {
                            if(self.isFavorite || movie.isFavorite){
                                Image(systemName: "star.fill")
                                    .foregroundColor(Color.yellow)
                            }
                            else{
                                Image(systemName: "star")
                                    .foregroundColor(Color.yellow)
                            }

                        }
                    }
                }
            }
            .padding(EdgeInsets.init(top: 10, leading: 10, bottom: 10, trailing: 10))
            .background(
                Rectangle()
                    .fill(Color.init("Alabaster"))
                    .addBorder(Color.init("Alto"), width: 1, cornerRadius: 20)
                
                
            )
            .listRowBackground(Color("trans"))
        }
        .buttonStyle(PlainButtonStyle())

    }
}

struct GrayCircle:View{
    var body: some View{
        ZStack{
            Circle().fill(Color.gray)
            ProgressView()
                .scaleEffect(1.2)
                .frame(width: 100,height: 100)
        }
        
    }
}

struct MovieItemUI_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
