package i.am.bored.datasource

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import i.am.bored.api.IdeaApiClient
import i.am.bored.api.IdeaTypeAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import model.Result
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class IdeaRemoteDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: IdeaApiClient

    private val client = OkHttpClient.Builder().build()

    private val moshi: Moshi = Moshi.Builder().add(IdeaTypeAdapter()).build()

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()

        apiClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // setting a dummy url
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(IdeaApiClient::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `correct response is parsed into success result`() = runTest {
        // given
        val response = MockResponse().setBody(successfulResponse).setResponseCode(200)
        mockWebServer.enqueue(response)
        val activityRemoteDataSource = IdeaRemoteDataSourceImpl(apiClient)
        val expectedActivity = responseIdea

        // when
        val result = activityRemoteDataSource.getIdea()

        // then
        assert(result is Result.Success)
        assertEquals((result as Result.Success).data, expectedActivity)
    }

    @Test
    fun `bad response returns json error result`() = runTest {
        // given
        val response = MockResponse().setBody(errorResponse).setResponseCode(200)
        mockWebServer.enqueue(response)
        val activityRemoteDataSource = IdeaRemoteDataSourceImpl(apiClient)

        // when
        val result = activityRemoteDataSource.getIdea()

        // then
        assert(result is Result.Error)
        assert((result as Result.Error).error is JsonDataException)
    }

    @Test
    fun `error response returns http error result`() = runTest {
        // given
        val response = MockResponse().setBody(errorResponse).setResponseCode(400)
        mockWebServer.enqueue(response)
        val activityRemoteDataSource = IdeaRemoteDataSourceImpl(apiClient)

        // when
        val result = activityRemoteDataSource.getIdea()

        // then
        assert(result is Result.Error)
        assert((result as Result.Error).error is HttpException)
    }
}