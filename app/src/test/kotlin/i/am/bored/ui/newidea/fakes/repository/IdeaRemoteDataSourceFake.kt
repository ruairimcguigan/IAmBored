package i.am.bored.ui.newidea.fakes.repository

import i.am.bored.ui.newidea.fakes.models.firstIdea
import model.IdeaDomain
import repository.IdeaRemoteDataSource

class IdeaRemoteDataSourceFake : IdeaRemoteDataSource {

    var getActivityWasCalled = false
        private set

    override suspend fun getIdea(): model.Result<IdeaDomain> {
        getActivityWasCalled = true
        return model.Result.Success(firstIdea)
    }
}
