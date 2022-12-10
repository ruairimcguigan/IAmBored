package repository

import androidx.lifecycle.LiveData
import model.IdeaDomain

interface IdeaLocalDataSource {
    suspend fun saveIdea(idea: IdeaDomain)

    suspend fun deleteIdea(idea: IdeaDomain)

    suspend fun isIdeaSaved(key: String): Boolean

    suspend fun getIdeasListLiveData(): List<IdeaDomain>
}
