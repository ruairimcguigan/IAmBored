package i.am.bored.datasource

import i.am.bored.api.MovieService
import i.am.bored.mapper.DataDomainMapper.toDomainModel
import model.Response
import model.movies.TopMovieDomainModel
import model.runCatching
import repository.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : RemoteDataSource {

    override suspend fun getTopRatedMovies(): Response<List<TopMovieDomainModel>> {
        return runCatching {
            movieService.getTopRated().map { it.toDomainModel() }
        }
    }
}
