package i.am.bored.interactors

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
class GetRandomIdeaInteractorImplTest {

    private val moviesRepository: MoviesRepository = mock()

    @Test
    fun `get random activity interacts with repository`() = runTest {
        // given
        val getRandomIdea = GetRandomIdeaInteractorImpl(moviesRepository)

        // when
        getRandomIdea()

        // then
        verify(moviesRepository, times(1)).getNewIdea()
    }
}