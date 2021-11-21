package com.example.dictionary.features.domain.repository

import com.example.dictionary.features.domain.model.WordDetails
import com.example.dictionary.handler.Response
import kotlinx.coroutines.flow.Flow

interface DictRepo {

    /*
        With flow we can emit multiple values
        we can emit Response then get the value,
        then emit another request after the response.
    */
    fun getWordDetails(word: String): Flow<Response<List<WordDetails>>>

}