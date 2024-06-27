package i.am.bored.repository

import i.am.bored.annotation.AppScope
import i.am.bored.annotation.IODispatcher
import i.am.bored.api.MovieService
import i.am.bored.mapper.DataDomainMapper.toDomainModel
import i.am.bored.mapper.DataDomainMapper.toEntityModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import model.movies.TopMovieDomainModel
import repository.LocalDataSource
import repository.RemoteDataSource
import repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    @AppScope private val appScope: CoroutineScope,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource,
    private val movieService: MovieService,
    private val localDataSource: LocalDataSource
) : MoviesRepository {

    override fun getMovies(): Flow<List<TopMovieDomainModel>> {
        return localDataSource.getTopRated()
            .map { moviesCached ->
                moviesCached.map { it.toDomainModel() }
            }.onEach { movies ->
                if (movies.isEmpty()) {
                    refreshTopRated()
                }
            }
    }

    override suspend fun refreshTopRated() {
         movieService.getTopRated()
            .map {
                it.toDomainModel().toEntityModel()
            }
            .also {
                localDataSource.saveTopRatedMovies(it)
            }
    }

    fun getNewActivityInANewCoroutine() {
        appScope.launch(ioDispatcher) {
            delay(1000)
            remoteDataSource.getTopRatedMovies()
        }
    }
}
