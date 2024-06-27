package i.am.bored.model

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val vote_average: Float,
    val genre_ids: List<Int>
)

data class MovieResponse(
    val results: List<Movie>
)
