package i.am.bored.mapper

import i.am.bored.model.TopMovie
import model.movies.TopMovieDomainModel

fun TopMovieDomainModel.toPresentationModel() = TopMovie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage
)