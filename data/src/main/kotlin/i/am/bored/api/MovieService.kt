package i.am.bored.api

import biz.filmeroo.premier.api.models.GenreResponse
import i.am.bored.model.MovieResponse
import i.am.bored.model.MovieDataModel
import i.am.bored.model.ApiFilmListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/top_rated")
    suspend fun getTopRated(): List<MovieDataModel>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movieId: Long): MovieDataModel

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?
    ): GenreResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY_PARAM = "api_key"

        fun buildImageUrl(path: String) = "https://image.tmdb.org/t/p/w500$path"
    }


}
