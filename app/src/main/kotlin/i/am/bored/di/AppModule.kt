package i.am.bored.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import i.am.bored.annotation.AppScope
import i.am.bored.annotation.IODispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @AppScope
    fun provideAppScope() = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @Provides
    @IODispatcher
    fun provideIODispatcher() = Dispatchers.IO
}
