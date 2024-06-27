package i.am.bored.datasource

import i.am.bored.db.MoviesDao
import kotlinx.coroutines.flow.Flow
import repository.LocalDataSource
import repository.local.TopMovieCached
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val moviesDao: MoviesDao
) : LocalDataSource {
    override fun getTopRated(): Flow<List<TopMovieCached>> = moviesDao.getTopRatedMovies()

    override suspend fun saveTopRatedMovies(movies: List<TopMovieCached>) {
        moviesDao.saveTopRatedMovies(movies)
    }


}
