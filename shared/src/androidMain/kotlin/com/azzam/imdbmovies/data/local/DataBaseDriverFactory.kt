package com.azzam.imdbmovies.data.local

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.azzam.imdbmovies.database.MoviesDatabase

actual class DataBaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            MoviesDatabase.Schema,
            context,
            "movies.db"
        )
    }
}