package i.am.bored.repository

import i.am.bored.datasource.firstIdea
import i.am.bored.ui.newidea.fakes.repository.IdeaLocalDataSourceFake
import i.am.bored.ui.newidea.fakes.repository.IdeaRemoteDataSourceFake
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import model.Result
import org.junit.Assert.*
import org.junit.Test

@ExperimentalCoroutinesApi
class IdeasRepositoryImplTest {

    @Test
    fun `getNewIdea() returns a result after switching the context`() = runTest {
        // given
        val ideasRepository = IdeasRepositoryImpl(
            appScope = this,
            ioDispatcher = StandardTestDispatcher(testScheduler),
            remoteDataSource = IdeaRemoteDataSourceFake(),
            localDataSource = IdeaLocalDataSourceFake()
        )

        val expected = firstIdea

        // when
        val result = ideasRepository.getNewIdea()

        // then
        assert(result is Result.Success)
        assertEquals((result as Result.Success).data, expected)
    }

    @Test
    fun `getNewIdeaInANewCoroutine correctly calls remote data source`() = runTest {
        // given
        val fakeRemoteRepository = IdeaRemoteDataSourceFake()
        val activityRepository = IdeasRepositoryImpl(
            appScope = this,
            ioDispatcher = StandardTestDispatcher(testScheduler),
            remoteDataSource = fakeRemoteRepository,
            localDataSource = IdeaLocalDataSourceFake()
        )

        // when
        activityRepository.getNewActivityInANewCoroutine()
        advanceUntilIdle()

        // then
        assert(fakeRemoteRepository.getActivityWasCalled)
    }
}
