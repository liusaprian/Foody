package app.liusaprian.foody.utils

import com.google.gson.*
import java.text.SimpleDateFormat
import java.util.*

object Helpers {
    fun getDefaultGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .registerTypeAdapter(Date::class.java, JsonDeserializer {json, _, _ ->
                val formatServer = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> {src, _, _ ->
                val format = SimpleDateFormat("", Locale.ENGLISH)
                format.timeZone = TimeZone.getTimeZone("UTC")
                if(src != null) JsonPrimitive(format.format(src))
                else null
            })
            .create()
    }

    fun Long.convertToDateTime(dateFormat: String): String {
        val date = Date(this)
        val format = SimpleDateFormat(dateFormat, Locale.ENGLISH)
        return format.format(date)
    }
}