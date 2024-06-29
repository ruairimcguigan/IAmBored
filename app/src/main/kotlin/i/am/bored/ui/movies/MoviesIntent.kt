package i.am.bored.ui.movies

sealed class MoviesIntent {
    data object RefreshMovies : MoviesIntent()

    data class MovieClicked(val uri: String) : MoviesIntent()
}
