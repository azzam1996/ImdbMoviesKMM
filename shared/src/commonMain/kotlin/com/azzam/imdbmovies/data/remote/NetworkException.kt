package com.azzam.imdbmovies.data.remote

class NetworkException(val code: Int? , val errorBody: String?): Exception() {
}