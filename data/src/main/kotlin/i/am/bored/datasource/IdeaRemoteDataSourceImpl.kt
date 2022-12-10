package i.am.bored.datasource

import i.am.bored.api.IdeaApiClient
import model.IdeaDomain
import model.Result
import model.runCatching
import repository.IdeaRemoteDataSource
import javax.inject.Inject

class IdeaRemoteDataSourceImpl @Inject constructor(
    private val ideaApiClient: IdeaApiClient
) : IdeaRemoteDataSource {

    override suspend fun getIdea(): Result<IdeaDomain> {
        return runCatching {
            ideaApiClient.getIdea()
        }
    }
}
