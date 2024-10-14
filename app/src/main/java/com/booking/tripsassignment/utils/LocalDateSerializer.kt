package com.booking.tripsassignment.utils

import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonDeserializationContext
import org.joda.time.format.ISODateTimeFormat
import com.google.gson.JsonSerializer
import com.google.gson.JsonDeserializer
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormatter
import java.lang.reflect.Type

/**
 * Gson serializer to help serialize [LocalDate] instances.
 * We have provided this utility so that you can use it if you need it.
 */
class LocalDateSerializer : JsonDeserializer<LocalDate?>,
    JsonSerializer<LocalDate?> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        je: JsonElement, type: Type?,
        jdc: JsonDeserializationContext?
    ): LocalDate? {
        val dateAsString = je.asString
        return if (dateAsString.isEmpty()) {
            null
        } else {
            DATE_FORMAT.parseLocalDate(dateAsString)
        }
    }

    override fun serialize(
        src: LocalDate?, typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val retVal: String = if (src == null) {
            ""
        } else {
            DATE_FORMAT.print(src)
        }
        return JsonPrimitive(retVal)
    }

    companion object {
        private val DATE_FORMAT: DateTimeFormatter = ISODateTimeFormat.date()
    }
}