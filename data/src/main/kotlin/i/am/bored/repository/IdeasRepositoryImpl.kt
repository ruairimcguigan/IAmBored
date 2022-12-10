package i.am.bored.repository

import i.am.bored.annotation.AppScope
import i.am.bored.annotation.IODispatcher
import kotlinx.coroutines.*
import model.IdeaDomain
import model.Result
import repository.IdeaLocalDataSource
import repository.IdeaRemoteDataSource
import repository.IdeasRepository
import javax.inject.Inject

class IdeasRepositoryImpl @Inject constructor(
    @AppScope private val appScope: CoroutineScope,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: IdeaRemoteDataSource,
    private val localDataSource: IdeaLocalDataSource
) : IdeasRepository {

    override suspend fun getNewIdea(): Result<IdeaDomain> {
        return withContext(ioDispatcher) {
            remoteDataSource.getIdea()
        }
    }

    override suspend fun saveIdea(idea: IdeaDomain) {
        localDataSource.saveIdea(idea)
    }

    override suspend fun deleteIdea(idea: IdeaDomain) {
        localDataSource.deleteIdea(idea)
    }

    override suspend fun isIdeaSaved(key: String): Boolean {
        return localDataSource.isIdeaSaved(key)
    }

    override suspend fun getIdeasLiveData(): List<IdeaDomain> {
        return localDataSource.getIdeasListLiveData()
    }

    fun getNewActivityInANewCoroutine() {
        appScope.launch(ioDispatcher) {
            delay(1000)
            remoteDataSource.getIdea()
        }
    }
}
