package i.am.bored.interactors

import i.am.bored.testmodels.firstIdea
import interactors.SaveIdeaInteractorImpl
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
class SaveActivityImplTest {

    private val ideasRepository: IdeasRepository = mock()

    @Test
    fun `save activity interacts with repository`() = runTest {
        // given
        val saveActivity = SaveIdeaInteractorImpl(ideasRepository)

        // when
        saveActivity(firstIdea)

        // then
        verify(ideasRepository, times(1)).saveIdea(firstIdea)
    }
}