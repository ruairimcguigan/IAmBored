package repository

import model.IdeaDomain
import model.Result

interface IdeasRepository {
    suspend fun getNewIdea(): Result<IdeaDomain>
    suspend fun saveIdea(idea: IdeaDomain)
    suspend fun deleteIdea(idea: IdeaDomain)
    suspend fun isIdeaSaved(key: String): Boolean
    suspend fun getIdeasLiveData(): List<IdeaDomain>
}
