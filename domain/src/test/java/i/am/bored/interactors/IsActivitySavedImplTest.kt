package i.am.bored.interactors

import i.am.bored.testmodels.firstIdea
import interactors.IsIdeaSavedInteractorImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import repository.IdeasRepository

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class IsActivitySavedImplTest {

    private val ideasRepository: IdeasRepository = mock()

    @Test
    fun `is activity saved interacts with repository`() = runTest {
        // given
        val isActivitySaved = IsIdeaSavedInteractorImpl(ideasRepository)

        // when
        isActivitySaved(firstIdea.key)

        // then
        verify(ideasRepository, times(1)).isIdeaSaved(firstIdea.key)
    }
}