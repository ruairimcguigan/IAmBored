package i.am.bored.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize data class TopMovie(
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String?,
    val voteAverage: String?
): Parcelable