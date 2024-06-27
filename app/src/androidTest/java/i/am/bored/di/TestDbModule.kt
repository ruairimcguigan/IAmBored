package i.am.bored.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import i.am.bored.db.AppDb
import i.am.bored.db.MoviesDao
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DbModule::class]
)
object TestDbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDb {
        return Room.inMemoryDatabaseBuilder(context, AppDb::class.java).build()
    }

    @Provides
    @Singleton
    fun provideIdeaDao(appDatabase: AppDb): MoviesDao = appDatabase.moviesDao()
}
