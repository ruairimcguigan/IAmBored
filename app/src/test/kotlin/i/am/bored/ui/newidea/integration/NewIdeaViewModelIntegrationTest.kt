package i.am.bored.ui.newidea.integration

import i.am.bored.api.IdeaApiClient
import i.am.bored.datasource.IdeaLocalDataSourceImpl
import i.am.bored.datasource.IdeaRemoteDataSourceImpl
import i.am.bored.db.IdeaDao
import i.am.bored.mapper.DataDomainMapper.mapDomainToData
import i.am.bored.repository.IdeasRepositoryImpl
import i.am.bored.ui.newidea.NewIdeaUiState
import i.am.bored.ui.newidea.NewIdeasViewModel
import i.am.bored.ui.newidea.fakes.models.firstIdea
import i.am.bored.ui.newidea.rules.CoroutineRule
import interactors.DeleteIdeaInteractorImpl
import interactors.GetRandomIdeaInteractorImpl
import interactors.IsIdeaSavedInteractorImpl
import interactors.SaveIdeaInteractorImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class NewIdeaViewModelIntegrationTest {

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testScope = TestScope(testDispatcher)

    @get:Rule
    val coroutineRule = CoroutineRule(testDispatcher)

    private val mockApiClient: IdeaApiClient = mock()
    private val mockIdeaDao: IdeaDao = mock()

    private val remoteDataSource = IdeaRemoteDataSourceImpl(mockApiClient)
    private val localDataSource = IdeaLocalDataSourceImpl(mockIdeaDao)

    private lateinit var classUnderTest: NewIdeasViewModel

    private val ideasRepository = IdeasRepositoryImpl(
        appScope = testScope,
        ioDispatcher = testDispatcher,
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )

    private val getRandomIdea = GetRandomIdeaInteractorImpl(ideasRepository)
    private val saveIdea = SaveIdeaInteractorImpl(ideasRepository)
    private val deleteIdea = DeleteIdeaInteractorImpl(ideasRepository)
    private val isIdeaSaved = IsIdeaSavedInteractorImpl(ideasRepository)

    @Test
    fun `calling loadNewIdea() triggers the api client`() = runTest {
        // given
        classUnderTest = NewIdeasViewModel(
            getRandomIdea,
            saveIdea,
            deleteIdea,
            isIdeaSaved
        )

        // when
        classUnderTest.loadNewIdea()
        runCurrent()

        // then
        verify(mockApiClient, times(1)).getIdea()
    }

    @Test
    fun `calling save idea triggers the dao insert`() = runTest {
        // given
        classUnderTest = NewIdeasViewModel(
            getRandomIdea,
            saveIdea,
            deleteIdea,
            isIdeaSaved
        )

        // when
        classUnderTest.setIsFavourite(firstIdea, true)
        runCurrent()

        // then
        verify(mockIdeaDao, times(1)).insert(mapDomainToData(firstIdea))
    }

    @Test
    fun `calling delete idea triggers the dao delete`() = runTest {
        // given
        classUnderTest = NewIdeasViewModel(
            getRandomIdea,
            saveIdea,
            deleteIdea,
            isIdeaSaved
        )

        // when
        classUnderTest.setIsFavourite(firstIdea, false)
        runCurrent()

        // then
        verify(mockIdeaDao, times(1)).delete(mapDomainToData(firstIdea))
    }

    @Test
    fun `loading idea triggers the dao exists`() = runTest {
        // when
        whenever(mockIdeaDao.isIdeaSaved(firstIdea.key)).doReturn(true)
        whenever(mockApiClient.getIdea()).doReturn(firstIdea)

        classUnderTest = NewIdeasViewModel(
            getRandomIdea,
            saveIdea,
            deleteIdea,
            isIdeaSaved
        )

        // when
        runCurrent()

        // then
        val actualState = classUnderTest.uiState.value as NewIdeaUiState.Success
        Assert.assertEquals(actualState.isFavourite, true)
    }
}
