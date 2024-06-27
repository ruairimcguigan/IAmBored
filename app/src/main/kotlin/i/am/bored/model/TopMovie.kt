package i.am.bored.model

data class TopMovie(
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String?,
    val voteAverage: String?
)