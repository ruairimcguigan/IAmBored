package model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class IdeaDomain (
    @field:Json(name = "activity")
    val name: String,
    val type: Type,
    @field:Json(name = "participants")
    @ColumnInfo(name = "participant_count")
    val participantCount: Int,
    val price: Float,
    val link: String,
    @PrimaryKey
    val key: String,
    val accessibility: Float
    )
