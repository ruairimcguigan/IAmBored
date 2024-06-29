package i.am.bored.ui.movies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import i.am.bored.ui.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    moviesInitialState: MoviesUiState,
): BaseViewModel<
        MoviesUiState,
        MoviesUiState.PartialState,
        MoviesEvent,
        MoviesIntent>(
            savedStateHandle = savedStateHandle,
            initialState = moviesInitialState
        ) {
    override fun mapIntents(intent: MoviesIntent): Flow<MoviesUiState.PartialState> {
        TODO("Not yet implemented")
    }

    override fun reduceUiState(
        previousState: MoviesUiState,
        partialState: MoviesUiState.PartialState
    ): MoviesUiState {
        TODO("Not yet implemented")
    }

}
