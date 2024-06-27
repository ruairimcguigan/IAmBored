package di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import interactors.movies.GetTopRatedMoviesInteractor
import interactors.movies.getTopRatedMovies
import repository.MoviesRepository

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    fun provideGetTopRatedMoviesInteractor(repository: MoviesRepository): GetTopRatedMoviesInteractor {
        return GetTopRatedMoviesInteractor {
            getTopRatedMovies(repository)
        }
    }
}
