package repository

import kotlinx.coroutines.flow.Flow
import model.movies.TopMovieDomainModel

interface MoviesRepository {

    fun getMovies(): Flow<List<TopMovieDomainModel>>

    suspend fun refreshTopRated()

}
