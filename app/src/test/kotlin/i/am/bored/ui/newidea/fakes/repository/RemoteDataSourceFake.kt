package i.am.bored.ui.newidea.fakes.repository

import i.am.bored.ui.newidea.fakes.models.firstIdea
import model.IdeaDomain
import repository.RemoteDataSource

class RemoteDataSourceFake : RemoteDataSource {

    var getActivityWasCalled = false
        private set

    override suspend fun getTopRatedMovies(): model.Response<IdeaDomain> {
        getActivityWasCalled = true
        return model.Response.Success(firstIdea)
    }
}
