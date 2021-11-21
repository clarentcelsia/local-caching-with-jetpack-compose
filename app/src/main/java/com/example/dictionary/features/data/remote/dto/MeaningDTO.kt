package com.example.dictionary.features.data.remote.dto

import com.example.dictionary.features.domain.model.Meaning

data class MeaningDTO(
    val definitions: List<DefinitionDTO>,
    val partOfSpeech: String
) {
    fun meaningMapper(): Meaning {
        return Meaning(definitions.map { it.definitionMapper() }, partOfSpeech)
    }
}