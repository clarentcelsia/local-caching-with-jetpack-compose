package com.example.dictionary.features.domain.model


data class WordDetails(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
)