package i.am.bored.interactors

import interactors.GetRandomIdeaInteractorImpl
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
class GetRandomIdeaInteractorImplTest {

    private val ideasRepository: IdeasRepository = mock()

    @Test
    fun `get random activity interacts with repository`() = runTest {
        // given
        val getRandomIdea = GetRandomIdeaInteractorImpl(ideasRepository)

        // when
        getRandomIdea()

        // then
        verify(ideasRepository, times(1)).getNewIdea()
    }
}