package i.am.bored.ui.newidea.viewmodels

import i.am.bored.ui.newidea.NewIdeaUiState
import i.am.bored.ui.newidea.NewIdeasViewModel
import i.am.bored.ui.newidea.fakes.interactors.DeleteIdeaInteractorFake
import i.am.bored.ui.newidea.fakes.interactors.GetRandomIdeaInteractorFake
import i.am.bored.ui.newidea.fakes.interactors.IsIdeaSavedFake
import i.am.bored.ui.newidea.fakes.interactors.SaveIdeaInteractorFake
import i.am.bored.ui.newidea.fakes.models.firstIdea
import i.am.bored.ui.newidea.fakes.models.secondIdea
import i.am.bored.ui.newidea.rules.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewIdeasViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var classUnderTest: NewIdeasViewModel

    @Test
    fun `creating NewIdeaViewModel exposes loading ui state`() {
        // given
        classUnderTest = NewIdeasViewModel(
            getRandomIdea = GetRandomIdeaInteractorFake(),
            saveIdea = SaveIdeaInteractorFake(),
            deleteIdea = DeleteIdeaInteractorFake(),
            isIdeaSaved = IsIdeaSavedFake()
        )
        // then
        assert(classUnderTest.uiState.value is NewIdeaUiState.Loading)
    }

    @Test
    fun `creating a viewmodel updates ui state to success after loading`() {
        // given
        classUnderTest = NewIdeasViewModel(
            getRandomIdea = GetRandomIdeaInteractorFake(),
            saveIdea = SaveIdeaInteractorFake(),
            deleteIdea = DeleteIdeaInteractorFake(),
            isIdeaSaved = IsIdeaSavedFake()
        )
        val expectedState = NewIdeaUiState.Success(idea = firstIdea, isFavourite = false)

        // when
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // then
        val actualState = classUnderTest.uiState.value
        assertEquals(expectedState, actualState)
    }

    @Test
    fun `creating a viewmodel updates ui state to error in case of failure`() {
        // given
        classUnderTest = NewIdeasViewModel(
            getRandomIdea = GetRandomIdeaInteractorFake(isSuccessful = false),
            saveIdea = SaveIdeaInteractorFake(),
            deleteIdea = DeleteIdeaInteractorFake(),
            isIdeaSaved = IsIdeaSavedFake()
        )

        // when
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // then
        val currentState = classUnderTest.uiState.value
        assert(currentState is NewIdeaUiState.Error)
    }

    @Test
    fun `if idea is already saved, ui state's isFavourite is set to true`() {
        // given
        classUnderTest = NewIdeasViewModel(
            getRandomIdea = GetRandomIdeaInteractorFake(),
            saveIdea = SaveIdeaInteractorFake(),
            deleteIdea = DeleteIdeaInteractorFake(),
            isIdeaSaved = IsIdeaSavedFake(isIdeaSaved = true)
        )

        val expectedUiState = NewIdeaUiState.Success(firstIdea, true)

        // when
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // then
        val actualState = classUnderTest.uiState.value
        assertEquals(actualState, expectedUiState)
    }

    @Test
    fun `calling loadNewIdea() updates ui state with a new idea`() {
        // given
        val getRandomIdeaFake = GetRandomIdeaInteractorFake()
        classUnderTest = NewIdeasViewModel(
            getRandomIdea = getRandomIdeaFake,
            saveIdea = SaveIdeaInteractorFake(),
            deleteIdea = DeleteIdeaInteractorFake(),
            isIdeaSaved = IsIdeaSavedFake()
        )
        coroutineRule.testDispatcher.scheduler.runCurrent()

        val expectedUiState = NewIdeaUiState.Success(secondIdea, false)
        getRandomIdeaFake.idea = secondIdea

        // when
        classUnderTest.loadNewIdea()
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // then
        val actualState = classUnderTest.uiState.value
        assertEquals(actualState, expectedUiState)
    }

    @Test
    fun `calling setIsFavourite(true) triggers SaveIdeaInteractor`() {
        // given
        val saveIdeaFake = SaveIdeaInteractorFake()
        val viewModel = NewIdeasViewModel(
            getRandomIdea = GetRandomIdeaInteractorFake(),
            saveIdea = saveIdeaFake,
            deleteIdea = DeleteIdeaInteractorFake(),
            isIdeaSaved = IsIdeaSavedFake()
        )

        // when
        viewModel.setIsFavourite(firstIdea, true)
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // then
        assert(saveIdeaFake.wasCalled)
    }

    @Test
    fun `calling setIsFavourite(false) triggers DeleteIdeaInteractor`() {
        // given
        val deleteIdea = DeleteIdeaInteractorFake()
        classUnderTest = NewIdeasViewModel(
            getRandomIdea = GetRandomIdeaInteractorFake(),
            saveIdea = SaveIdeaInteractorFake(),
            deleteIdea = deleteIdea,
            isIdeaSaved = IsIdeaSavedFake()
        )

        // when
        classUnderTest.setIsFavourite(firstIdea, false)
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // then
        assert(deleteIdea.wasCalled)
    }

    @Test
    fun `calling setIsFavourite(true) changes the ui state's isFavourite flag`() {
        // given
        val saveIdea = SaveIdeaInteractorFake()
        classUnderTest = NewIdeasViewModel(
            getRandomIdea = GetRandomIdeaInteractorFake(),
            saveIdea = saveIdea,
            deleteIdea = DeleteIdeaInteractorFake(),
            isIdeaSaved = IsIdeaSavedFake()
        )

        val expectedUiState = NewIdeaUiState.Success(firstIdea, true)

        // when
        classUnderTest.setIsFavourite(firstIdea, true)
        coroutineRule.testDispatcher.scheduler.runCurrent()

        // then
        val actualState = classUnderTest.uiState.value
        assertEquals(actualState, expectedUiState)
    }
}
