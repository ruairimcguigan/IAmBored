package model.movies

data class TopMovieDomainModel(
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String?,
    val voteAverage: String?
)
