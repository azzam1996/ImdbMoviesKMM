//
//  DisplayMovieUI.swift
//  iosApp
//
//  Created by Azzam Habib on 3/21/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import Kingfisher

struct DisplayMovieUI: View {
    let movie: Movie
    @Binding var isShowing: Bool
    
    var body: some View {
        GeometryReader{ geo in
            VStack{
                HStack{
                    Spacer()
                    Button(action:{
                        isShowing = false
                    }){
                        Image(systemName: "xmark")
                            .resizable()
                            .foregroundColor(Color.gray)
                            .frame(width: 20,height: 20)
                    }

                }
                .padding(EdgeInsets.init(top: 60, leading: 0, bottom: 20, trailing: 0))
                
                VStack{
                    KFImage(URL(string: movie.image  ?? "placeholder"))
                        .placeholder{
                            GrayCircle()
                        }
                        .resizable()
                        .clipShape(Circle())
                        .frame(width: 200, height: 200)
                    
                    Text(movie.fullTitle ?? movie.title ?? "")
                        .font(.custom("sans-serif", size: 20))
                        .fontWeight(.semibold)
                        .multilineTextAlignment(.center)
                        .padding(.top, 20)
                        .lineLimit(2)
                    
                    Text(movie.crew ?? "")
                        .font(.custom("sans-serif", size: 15))
                        .fontWeight(.semibold)
                        .multilineTextAlignment(.center)
                        .padding(.top, 20)
                        .lineLimit(4)
                    
                    HStack(alignment: .center){
                        Image(systemName: "star.fill")
                            .foregroundColor(Color.yellow)
                        
                        Text(movie.imDbRating ?? "")
                            .font(.custom("sans-serif", size: 15))
                            .fontWeight(.medium)
                            .multilineTextAlignment(.center)
                            .lineLimit(2)
                            
                    }
                    .padding(.top, 20)
                }
                .frame(
                    minWidth: 0,
                    maxWidth: .infinity,
                    minHeight: 0,
                    maxHeight: 400,
                    alignment: .center
                )
                .padding(EdgeInsets.init(top: 50, leading: 20, bottom: 20, trailing: 20))
                .background(
                    RoundedRectangle(cornerRadius: 20)
                        .foregroundColor(Color.white)
                )
            
            }
        }
    }
}
