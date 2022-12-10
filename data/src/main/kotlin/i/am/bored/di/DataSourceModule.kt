package i.am.bored.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import i.am.bored.datasource.IdeaLocalDataSourceImpl
import i.am.bored.datasource.IdeaRemoteDataSourceImpl
import i.am.bored.repository.IdeasRepositoryImpl
import repository.IdeaLocalDataSource
import repository.IdeaRemoteDataSource
import repository.IdeasRepository

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindActivityRepository(impl: IdeasRepositoryImpl): IdeasRepository

    @Binds
    abstract fun bindActivityRemoteDataSource(impl: IdeaRemoteDataSourceImpl): IdeaRemoteDataSource

    @Binds
    abstract fun bindActivityLocalDataSource(impl: IdeaLocalDataSourceImpl): IdeaLocalDataSource

}
