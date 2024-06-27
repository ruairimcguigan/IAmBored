package repository

import model.Response
import model.movies.TopMovieDomainModel

interface RemoteDataSource {
    suspend fun getTopRatedMovies(): Response<List<TopMovieDomainModel>>
}
