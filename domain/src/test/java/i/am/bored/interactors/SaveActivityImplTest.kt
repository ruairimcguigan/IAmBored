package i.am.bored.interactors

import i.am.bored.testmodels.firstIdea
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import repository.MoviesRepository

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SaveActivityImplTest {

    private val moviesRepository: MoviesRepository = mock()

    @Test
    fun `save activity interacts with repository`() = runTest {
        // given
        val saveActivity = SaveIdeaInteractorImpl(moviesRepository)

        // when
        saveActivity(firstIdea)

        // then
        verify(moviesRepository, times(1)).saveIdea(firstIdea)
    }
}