package interactors.movies

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import model.movies.TopMovieDomainModel
import repository.MoviesRepository
import java.io.IOException

fun interface GetTopRatedMoviesInteractor: () -> Flow<Result<List<TopMovieDomainModel>>>

private const val RETRY_TIME_IN_MILLIS = 15_000L

fun getTopRatedMovies(repository: MoviesRepository): Flow<Result<List<TopMovieDomainModel>>> = repository
    .getMovies()
    .map {
        Result.success(it)
    }
    .retryWhen { cause, _ ->
        if (cause is IOException) {
            emit(Result.failure(cause))

            delay(RETRY_TIME_IN_MILLIS)
            true
        } else {
            false
        }
    }
    .catch { // for other than IOException but it will stop collecting Flow
        emit(Result.failure(it)) // also catch does re-throw CancellationException
    }