package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import interactors.*

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindGetRandomIdeaInteractor(impl: GetRandomIdeaInteractorImpl): GetRandomIdeaInteractor

    @Binds
    abstract fun bindSaveIdeaInteractor(impl: SaveIdeaInteractorImpl): SaveIdeaInteractor

    @Binds
    abstract fun bindDeleteIdeaInteractor(impl: DeleteIdeaInteractorImpl): DeleteIdeaInteractor

    @Binds
    abstract fun bindIsIdeaSavedInteractor(impl: IsIdeaSavedInteractorImpl): IsIdeaSavedInteractor

    @Binds
    abstract fun bindGetFavouriteIdeasInteractor(impl: GetFavouriteIdeasInteractorImpl): GetFavouriteIdeasInteractor
}
