package i.am.bored.ui.movies

sealed class MoviesEvent {
    data class OpenWebBrowserWithDetails(val uri: String) : MoviesEvent()
}
