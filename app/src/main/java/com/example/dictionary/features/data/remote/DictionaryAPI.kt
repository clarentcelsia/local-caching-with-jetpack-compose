package com.example.dictionary.features.data.remote

import com.example.dictionary.features.data.remote.dto.WordDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryAPI {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWord(
        @Path("word") word: String
    ): List<WordDetailsDTO>

    companion object{
        const val BASE_URL = "https://api.dictionaryapi.dev"
    }
}