package i.am.bored.coroutines

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.krzdabrowski.starter.core.coroutines.DefaultDispatcher
import eu.krzdabrowski.starter.core.coroutines.IoDispatcher
import eu.krzdabrowski.starter.core.coroutines.MainImmediateDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention
@Qualifier
annotation class MainImmediateScope // UI-related

@Retention
@Qualifier
annotation class IoScope // I/O-related

@Retention
@Qualifier
annotation class DefaultScope // CPU-related

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutinesScopeModule {

    @Singleton
    @Provides
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    @MainImmediateScope
    @Singleton
    @Provides
    fun provideMainImmediateScope(
        @MainImmediateDispatcher mainImmediateDispatcher: CoroutineDispatcher,
        exceptionHandler: CoroutineExceptionHandler,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + mainImmediateDispatcher + exceptionHandler)

    @IoScope
    @Singleton
    @Provides
    fun provideIoScope(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        exceptionHandler: CoroutineExceptionHandler,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher + exceptionHandler)

    @DefaultScope
    @Singleton
    @Provides
    fun provideDefaultScope(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher,
        exceptionHandler: CoroutineExceptionHandler,
    ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher + exceptionHandler)
}
