package com.example.dictionary.features.presentation

import com.example.dictionary.features.domain.model.WordDetails

data class DictionaryState(
    val wordDetailsItem: List<WordDetails> = emptyList(),
    val isLoading: Boolean = false
)