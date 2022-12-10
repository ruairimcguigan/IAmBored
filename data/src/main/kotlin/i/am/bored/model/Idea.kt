package i.am.bored.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import model.Type

@Entity
data class Idea(
    @field:Json(name = "activity")
    val name: String,
    val type: String,
    @field:Json(name = "participants")
    @ColumnInfo(name = "participant_count")
    val participantCount: Int,
    val price: Float,
    val link: String,
    @PrimaryKey
    val key: String,
    val accessibility: Float
)
