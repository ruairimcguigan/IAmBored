package i.am.bored.db

import androidx.room.Database
import androidx.room.RoomDatabase
import repository.local.TopMovieCached

private const val DATABASE_VERSION = 1

@Database(
    entities = [TopMovieCached::class],
    version = DATABASE_VERSION
)
abstract class AppDb : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}

