package i.am.bored.mapper

import i.am.bored.model.Idea
import i.am.bored.model.MovieDataModel
import model.IdeaDomain
import model.Type
import model.movies.TopMovieDomainModel
import repository.local.TopMovieCached

object DataDomainMapper {

    fun TopMovieDomainModel.toEntityModel() = TopMovieCached(
        id =  id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )

    fun MovieDataModel.toDomainModel() = TopMovieDomainModel(
        id =  id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )

    fun TopMovieCached.toDomainModel() = TopMovieDomainModel(
        id =  id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage
    )

    fun mapDomainToData(idea: IdeaDomain): Idea {
        return Idea(
            name = idea.name,
            type = idea.type.toString(),
            participantCount = idea.participantCount,
            price = idea.price,
            link = idea.link,
            key = idea.key,
            accessibility = idea.accessibility
        )
    }

    fun mapDataToDomain(idea: Idea): IdeaDomain {
        return IdeaDomain(
            name = idea.name,
            type = mapIdeaTypeToString(idea.type),
            participantCount = idea.participantCount,
            price = idea.price,
            link = idea.link,
            key = idea.key,
            accessibility = idea.accessibility
        )
    }

    fun mapLiveDataDomainToData(data: List<Idea>): List<IdeaDomain> =
        data.map(this::mapDataToDomain)

    fun mapIdeaType(typeEnum: Type) = when (typeEnum) {
        Type.Education -> "Education"
        Type.Recreational -> "Recreational"
        Type.Social -> "Social"
        Type.Diy -> "Diy"
        Type.Charity -> "Charity"
        Type.Cooking -> "Cooking"
        Type.Relaxation -> "Relaxation"
        Type.Music -> "Music"
        Type.Busywork -> "Busywork"
        Type.Idle -> ""
    }

    fun mapIdeaTypeToString(typeEnum: String): Type = when (typeEnum) {
        "Education" -> Type.Education
        "Recreational" -> Type.Recreational
        "Social" -> Type.Social
        "Diy" -> Type.Diy
        "Charity" -> Type.Charity
        "Cooking" -> Type.Cooking
        "Relaxation" -> Type.Relaxation
        "Music" -> Type.Music
        "Busywork" -> Type.Busywork
        else -> Type.Idle
    }
}
