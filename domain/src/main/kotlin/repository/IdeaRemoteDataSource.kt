package repository

import model.IdeaDomain
import model.Result

interface IdeaRemoteDataSource {
    suspend fun getIdea(): Result<IdeaDomain>
}
