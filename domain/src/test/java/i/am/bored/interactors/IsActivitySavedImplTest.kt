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
class IsActivitySavedImplTest {

    private val moviesRepository: MoviesRepository = mock()

    @Test
    fun `is activity saved interacts with repository`() = runTest {
        // given
        val isActivitySaved = IsIdeaSavedInteractorImpl(moviesRepository)

        // when
        isActivitySaved(firstIdea.key)

        // then
        verify(moviesRepository, times(1)).isIdeaSaved(firstIdea.key)
    }
}