package i.am.bored.repository

import i.am.bored.datasource.firstIdea
import i.am.bored.ui.newidea.fakes.repository.LocalDataSourceFake
import i.am.bored.ui.newidea.fakes.repository.RemoteDataSourceFake
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import model.Response
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesRepositoryImplTest {

    @Test
    fun `getNewIdea() returns a result after switching the context`() = runTest {
        // given
        val ideasRepository = MoviesRepositoryImpl(
            appScope = this,
            ioDispatcher = StandardTestDispatcher(testScheduler),
            remoteDataSource = RemoteDataSourceFake(),
            localDataSource = LocalDataSourceFake()
        )

        val expected = firstIdea

        // when
        val result = ideasRepository.getNewIdea()

        // then
        assert(result is Response.Success)
        assertEquals((result as Response.Success).data, expected)
    }

    @Test
    fun `getNewIdeaInANewCoroutine correctly calls remote data source`() = runTest {
        // given
        val fakeRemoteRepository = RemoteDataSourceFake()
        val activityRepository = MoviesRepositoryImpl(
            appScope = this,
            ioDispatcher = StandardTestDispatcher(testScheduler),
            remoteDataSource = fakeRemoteRepository,
            localDataSource = LocalDataSourceFake()
        )

        // when
        activityRepository.getNewActivityInANewCoroutine()
        advanceUntilIdle()

        // then
        assert(fakeRemoteRepository.getActivityWasCalled)
    }
}
