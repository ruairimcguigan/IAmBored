package i.am.bored.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import i.am.bored.datasource.LocalDataSourceImpl
import i.am.bored.datasource.RemoteDataSourceImpl
import i.am.bored.repository.MoviesRepositoryImpl
import repository.LocalDataSource
import repository.RemoteDataSource
import repository.MoviesRepository

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindActivityRepository(impl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun bindActivityRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindActivityLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource

}
