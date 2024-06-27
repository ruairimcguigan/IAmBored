package i.am.bored.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import i.am.bored.db.AppDb
import i.am.bored.db.MoviesDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): AppDb {
        return Room.databaseBuilder(
            context,
            AppDb::class.java, "app-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideActivityDao(appDb: AppDb): MoviesDao {
        return appDb.moviesDao()
    }
}
