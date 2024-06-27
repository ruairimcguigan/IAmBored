package biz.filmeroo.premier.api.models

data class Genre(val id: Int = 0, val name: String)

data class GenreResponse(var genres: List<Genre>)
