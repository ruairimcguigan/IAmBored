package i.am.bored.ui.movies

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import i.am.bored.model.TopMovie
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class MoviesUiState(
    val isLoading: Boolean = false,
    val topMovies: List<TopMovie> = emptyList(),
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState()
        data class Fetched(val list: List<TopMovie>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
