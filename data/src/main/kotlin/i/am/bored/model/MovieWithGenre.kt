package biz.filmeroo.premier.api.models

data class MovieWithGenre(
    val id: Long,
    val title: String,
    val posterPath: String?,
    val voteAverage: Float,
    val genreNames: List<String>
)
data class MoviesWithGenresResponse(
    val results: List<MovieWithGenre>
)
