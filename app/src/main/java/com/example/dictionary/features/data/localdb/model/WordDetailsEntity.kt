package com.example.dictionary.features.data.localdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionary.features.domain.model.Meaning
import com.example.dictionary.features.domain.model.WordDetails

/* Connect to database */
@Entity(tableName = "tbWords")
data class WordDetailsEntity(
    val word: String,
    val phonetic: String,
    val origin: String,
    val meanings: List<Meaning>,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
){
    fun wordDetailsMapper(): WordDetails{
        return WordDetails(meanings, origin, phonetic, word)
    }
}