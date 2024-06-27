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
class DeleteActivityImplTest {

    private val moviesRepository: MoviesRepository = mock()

    @Test
    fun `deleteIdea interacts with repository`() = runTest {
        // given
        val deleteActivity = DeleteIdeaInteractorImpl(moviesRepository)

        // when
        deleteActivity(firstIdea)

        // then
        verify(moviesRepository, times(1)).deleteIdea(firstIdea)
    }
}