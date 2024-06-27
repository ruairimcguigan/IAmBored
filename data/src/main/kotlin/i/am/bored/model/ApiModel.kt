package i.am.bored.model

import com.squareup.moshi.Json

data class ApiFilmListResponse(val results: List<MovieDataModel>)

data class MovieDataModel(
    val id: Long,
    val title: String,
    val overview: String,
    @field:Json(name = "poster_path") val posterPath: String,
    @field:Json(name = "backdrop_path") val backdropPath: String?,
    @field:Json(name = "vote_average") val voteAverage: String?
)
