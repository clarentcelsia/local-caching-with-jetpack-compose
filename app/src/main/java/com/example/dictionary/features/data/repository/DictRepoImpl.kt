package com.example.dictionary.features.data.repository

import com.example.dictionary.features.data.localdb.dao.DictDao
import com.example.dictionary.features.data.remote.DictionaryAPI
import com.example.dictionary.features.domain.model.WordDetails
import com.example.dictionary.features.domain.repository.DictRepo
import com.example.dictionary.handler.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

// Single Source of Truth*
class DictRepoImpl(
    private val api: DictionaryAPI,
    private val dao: DictDao
) : DictRepo {
    override fun getWordDetails(word: String): Flow<Response<List<WordDetails>>> = flow {
        emit(Response.Loading())

        // [wordDetails]
        val getWordsFromDb = dao.getWords(word).map { it.wordDetailsMapper() }
        emit(Response.Loading(data = getWordsFromDb))

        try {
            val getWordsFromServer = api.getWord(word)
            dao.deleteDictFromDB(getWordsFromServer.map { it.word })
            dao.insertDictToDB(getWordsFromServer.map { it.wordDetailsMapper() })

        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    emit(Response.Error(
                            "Something went wrong!",
                            data = getWordsFromDb
                        ))}

                is IOException -> {
                    emit(Response.Error(
                            message = "Couldn't reach server, check your connection",
                            data = getWordsFromDb
                        ))}
            }
        }

        /*
        *   Get new data from localDb(repo) then map it to domain(VM)
        *   That will transfer the data to View/UI
        */
        val newWordDetails = dao.getWords(word).map { it.wordDetailsMapper() }
        emit(Response.Success(data = newWordDetails))
    }
}