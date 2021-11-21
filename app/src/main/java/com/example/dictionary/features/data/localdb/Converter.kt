package com.example.dictionary.features.data.localdb

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionary.features.data.util.JsonParser
import com.example.dictionary.features.domain.model.Meaning
import com.google.gson.reflect.TypeToken

/*
*   Convert List<Meaning> because database cannot
*   save list with data type Meaning.
*   Separating list in another table, is what supposed to be
*   for Relational Database.
*
*   Except for this case, it'll just convert the list into string
*   to make it simple.
*
*   Put any parsers that implemented by JsonParser.
*   @ProvidedTypeConverter give the possibility to take control
*   over the instantiate of a type converter(jsonParser).
*/
@ProvidedTypeConverter
class Converter(
    private val jsonParser: JsonParser
) {

    @TypeConverter
    fun fromMeaningJson(json: String): List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        )?: emptyList()
    }

    /* [] Json Null List Format */
    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>): String{
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        )?: "[]"
    }
}