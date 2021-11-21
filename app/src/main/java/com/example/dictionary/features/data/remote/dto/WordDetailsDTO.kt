package com.example.dictionary.features.data.remote.dto

import com.example.dictionary.features.data.localdb.model.WordDetailsEntity
import com.example.dictionary.features.domain.model.WordDetails

data class WordDetailsDTO(
    val meanings: List<MeaningDTO>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDTO>,
    val word: String
){
    fun wordDetailsMapper(): WordDetailsEntity{
        return WordDetailsEntity(
            word, phonetic, origin,
            meanings.map { it.meaningMapper() }
        )
    }
}