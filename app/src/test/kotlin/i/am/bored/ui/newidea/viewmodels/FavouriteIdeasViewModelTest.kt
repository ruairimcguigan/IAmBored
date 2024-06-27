package i.am.bored.ui.newidea.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import i.am.bored.ui.favourite.FavouriteIdeasUiState
import i.am.bored.ui.favourite.FavouriteIdeasViewModel
import i.am.bored.ui.newidea.fakes.models.firstIdea
import i.am.bored.ui.newidea.fakes.models.secondIdea
import i.am.bored.ui.newidea.rules.CoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class FavouriteIdeasViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Captor
    private lateinit var ideasCaptor: ArgumentCaptor<FavouriteIdeasUiState>

    private val getFavouriteIdeas: GetFavouriteIdeasInteractor = mock()
    private val deleteIdea: DeleteIdeaInteractor = mock()
    private val ideasObserver: Observer<FavouriteIdeasUiState> = mock()

    private lateinit var classUnderTest: FavouriteIdeasViewModel


    @Test
    fun `the viewmodel maps list of ideas to list ui state`(): Unit = runBlocking {
        // given
        classUnderTest  = FavouriteIdeasViewModel(
            getFavouriteIdeas,
            deleteIdea
        )

        val expectedList = listOf(firstIdea, secondIdea)
        whenever(getFavouriteIdeas.invoke()).thenReturn(listOf(firstIdea, secondIdea))

        // when
        classUnderTest.ideasLiveData.observeForever(ideasObserver)

        // then
        verify(ideasObserver, times(1)).onChanged(ideasCaptor.capture())
        assert(ideasCaptor.value is FavouriteIdeasUiState.Ideas)

        val actualList = (ideasCaptor.value as FavouriteIdeasUiState.Ideas).ideaList
        assertEquals(expectedList, actualList)

    }

    @Test
    fun `calling deleteIdea() interacts with the correct interactor`() = runTest {
        // given
        classUnderTest = FavouriteIdeasViewModel(
            getFavouriteIdeas,
            deleteIdea
        )

        // when
        classUnderTest.deleteIdea(firstIdea)
        advanceUntilIdle() // works the same as runCurrent() in this case

        // then
        verify(deleteIdea, times(1)).invoke(firstIdea)
    }
}
