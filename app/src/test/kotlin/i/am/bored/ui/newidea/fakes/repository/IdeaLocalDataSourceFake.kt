package i.am.bored.ui.newidea.fakes.repository

import model.IdeaDomain
import repository.IdeaLocalDataSource

class IdeaLocalDataSourceFake : IdeaLocalDataSource {

    override suspend fun saveIdea(idea: IdeaDomain) {
        // save
    }

    override suspend fun deleteIdea(idea: IdeaDomain) {
       // delete
    }

    override suspend fun isIdeaSaved(key: String): Boolean {
       return false
    }

    override suspend fun getIdeasListLiveData(): List<IdeaDomain> {
        return emptyList()
    }
}
