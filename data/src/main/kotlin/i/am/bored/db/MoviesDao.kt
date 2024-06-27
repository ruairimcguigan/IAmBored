package i.am.bored.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import repository.local.TopMovieCached

@Dao
interface MoviesDao {
    @Query("SELECT * FROM TopMovieCached")
    fun getTopRatedMovies(): Flow<List<TopMovieCached>>

    @Upsert
    suspend fun saveTopRatedMovies(movies: List<TopMovieCached>)
}
