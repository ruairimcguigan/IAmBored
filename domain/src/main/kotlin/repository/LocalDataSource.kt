package repository

import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import repository.local.TopMovieCached

interface LocalDataSource {
    @Query("SELECT * FROM TopMovieCached")
    fun getTopRated(): Flow<List<TopMovieCached>>

    @Upsert
    suspend fun saveTopRatedMovies(movies: List<TopMovieCached>)
}
