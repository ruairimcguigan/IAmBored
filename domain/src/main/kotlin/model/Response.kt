package model

sealed class Response<out T : Any> {
    data class Success<out T : Any>(val data: T): Response<T>()
    data class Error(val error: Exception): Response<Nothing>()
}

inline fun <T, R : Any> T.runCatching(block: T.() -> R): Response<R> {
    return try {
        Response.Success(block())
    } catch (e: Exception) {
        Response.Error(e)
    }
}
