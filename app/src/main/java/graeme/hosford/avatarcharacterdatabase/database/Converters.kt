package graeme.hosford.avatarcharacterdatabase.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun stringListToJson(list: List<String>?): String? {
            val moshi = Moshi.Builder().build()
            val type = Types.newParameterizedType(List::class.java, String::class.java)
            val adapter = moshi.adapter<List<String>>(type)
            return adapter.toJson(list)
        }

        @TypeConverter
        @JvmStatic
        fun jsonToStringList(json: String?): List<String>? {
            if (json == null) {
                return null
            }

            val moshi = Moshi.Builder().build()
            val type = Types.newParameterizedType(List::class.java, String::class.java)
            val adapter = moshi.adapter<List<String>>(type)
            return adapter.fromJson(json)
        }
    }


}