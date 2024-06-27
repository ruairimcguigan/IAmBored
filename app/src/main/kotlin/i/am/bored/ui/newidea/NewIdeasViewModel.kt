package i.am.bored.ui.newidea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import i.am.bored.mapper.toPresentationModel
import i.am.bored.ui.newidea.NewIdeaUiState.Success
import interactors.movies.GetTopRatedMoviesInteractor
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import model.IdeaDomain
import model.Response
import javax.inject.Inject

@HiltViewModel
class NewIdeasViewModel @Inject constructor(
    private val getTopRatedMoviesInteractor: GetTopRatedMoviesInteractor,
): ViewModel() {

    private val _uiState = MutableStateFlow<NewIdeaUiState>(NewIdeaUiState.Loading)
    val uiState: StateFlow<NewIdeaUiState> = _uiState

    private var loadingJob: Job? = null

    init {
       observeRockets()
    }

    private fun observeRockets() {
        viewModelScope.launch {
            getTopRatedMoviesInteractor()
                .map { result ->
                    result.fold(
                        onSuccess = { movieList ->
                            println( movieList.map { it.toPresentationModel() })
                        },
                        onFailure = {
                            Error(it)
                        },
                    )
                }
                .onStart {
                    emit(NewIdeaUiState.Loading)
                }
        }


//        fun loadNewIdea() {
//            _uiState.value = NewIdeaUiState.Loading
//
//            loadingJob?.cancel()
//            loadingJob = viewModelScope.launch {
//                when (val activityResult = getRandomIdea()) {
//                    is Response.Success ->
//                        _uiState.value = Success(
//                            activityResult.data,
//                            isIdeaSaved(activityResult.data.key)
//                        )
//
//                    is Response.Error ->
//                        _uiState.value = activityResult.error
//                            .takeUnless { it is CancellationException }
//                            ?.let(NewIdeaUiState::Error)
//                            ?: NewIdeaUiState.Loading
//                }
//            }
//        }
//
//        fun setIsFavourite(idea: IdeaDomain, isFavourite: Boolean) {
//            viewModelScope.launch {
//                if (isFavourite) saveIdea(idea) else deleteIdea(idea)
//                _uiState.value = Success(idea, isFavourite)
//            }
//        }
    }
}
