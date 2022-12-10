package i.am.bored.datasource

import i.am.bored.db.IdeaDao
import i.am.bored.mapper.DataDomainMapper.mapDomainToData
import i.am.bored.mapper.DataDomainMapper.mapLiveDataDomainToData
import model.IdeaDomain
import repository.IdeaLocalDataSource
import javax.inject.Inject

class IdeaLocalDataSourceImpl @Inject constructor(
    private val ideaDao: IdeaDao
) : IdeaLocalDataSource {

    override suspend fun saveIdea(idea: IdeaDomain) {
        ideaDao.insert(mapDomainToData(idea))
    }

    override suspend fun deleteIdea(idea: IdeaDomain) {
        ideaDao.delete(mapDomainToData(idea))
    }

    override suspend fun isIdeaSaved(key: String): Boolean {
        return ideaDao.isIdeaSaved(key)
    }

    override suspend fun getIdeasListLiveData(): List<IdeaDomain> {
        return mapLiveDataDomainToData(ideaDao.getAll())
    }
}
