package com.azzam.imdbmovies.data.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.azzam.imdbmovies.database.MoviesDatabase

actual class DataBaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MoviesDatabase.Schema, "movies.db")
    }
}