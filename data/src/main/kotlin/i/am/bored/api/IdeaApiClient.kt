package i.am.bored.api

import model.IdeaDomain
import retrofit2.http.GET

interface IdeaApiClient {
    @GET("/api/activity")
    suspend fun getIdea(): IdeaDomain
}
