package i.am.bored.ui.newidea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import i.am.bored.ui.newidea.NewIdeaUiState.Success
import interactors.DeleteIdeaInteractor
import interactors.GetRandomIdeaInteractor
import interactors.IsIdeaSavedInteractor
import interactors.SaveIdeaInteractor
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import model.IdeaDomain
import model.Result
import javax.inject.Inject

@HiltViewModel
class NewIdeasViewModel @Inject constructor(
    private val getRandomIdea: GetRandomIdeaInteractor,
    private val saveIdea: SaveIdeaInteractor,
    private val deleteIdea: DeleteIdeaInteractor,
    private val isIdeaSaved: IsIdeaSavedInteractor
): ViewModel() {

    private val _uiState = MutableStateFlow<NewIdeaUiState>(NewIdeaUiState.Loading)
    val uiState: StateFlow<NewIdeaUiState> = _uiState

    private var loadingJob: Job? = null

    init {
        loadNewIdea()
    }

    fun loadNewIdea() {
        _uiState.value = NewIdeaUiState.Loading

        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            when (val activityResult = getRandomIdea()) {
                is Result.Success ->
                    _uiState.value = Success(
                        activityResult.data,
                        isIdeaSaved(activityResult.data.key)
                    )
                is Result.Error ->
                    _uiState.value = activityResult.error
                        .takeUnless { it is CancellationException }
                        ?.let(NewIdeaUiState::Error)
                        ?: NewIdeaUiState.Loading
            }
        }
    }

    fun setIsFavourite(idea: IdeaDomain, isFavourite: Boolean) {
        viewModelScope.launch {
            if (isFavourite) saveIdea(idea) else deleteIdea(idea)
            _uiState.value = Success(idea, isFavourite)
        }
    }
}
