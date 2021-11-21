package com.example.dictionary.features.domain.use_case

import com.example.dictionary.features.domain.model.WordDetails
import com.example.dictionary.features.domain.repository.DictRepo
import com.example.dictionary.handler.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordDetails(
    private val dictRepo: DictRepo
) {

    /* This class can be called as if it's a function */
    operator fun invoke(word: String): Flow<Response<List<WordDetails>>>{
        if(word.isBlank()) return flow { }
        return dictRepo.getWordDetails(word)
    }
}