package i.am.bored.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import model.Type

@Suppress("unused")
class IdeaTypeAdapter {
    @ToJson
    fun toJson(type: Type): String {
        return type.toString().lowercase()
    }

    @FromJson
    fun fromJson(string: String): Type {
        return Type.valueOf(string.replaceFirstChar(Char::uppercase))
    }
}
