package com.example.dictionary.features.data.remote.dto

import com.example.dictionary.features.domain.model.Definition

data class DefinitionDTO(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
){
    // This gonna map the Definition in Data Package into Definition in Model Package.
    fun definitionMapper(): Definition{
        return Definition(
            antonyms, definition, example, synonyms
        )
    }
}