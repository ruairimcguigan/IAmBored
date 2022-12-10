package i.am.bored.interactors

import i.am.bored.testmodels.firstIdea
import interactors.DeleteIdeaInteractorImpl
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
class DeleteActivityImplTest {

    private val ideasRepository: IdeasRepository = mock()

    @Test
    fun `deleteIdea interacts with repository`() = runTest {
        // given
        val deleteActivity = DeleteIdeaInteractorImpl(ideasRepository)

        // when
        deleteActivity(firstIdea)

        // then
        verify(ideasRepository, times(1)).deleteIdea(firstIdea)
    }
}