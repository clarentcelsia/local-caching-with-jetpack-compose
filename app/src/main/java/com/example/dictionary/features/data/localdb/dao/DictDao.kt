package com.example.dictionary.features.data.localdb.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.dictionary.features.data.localdb.model.WordDetailsEntity

@Dao
interface DictDao {

    @Query("SELECT * FROM tbWords WHERE word LIKE '%' || :word || '%'")
    suspend fun getWords(word: String): List<WordDetailsEntity>

    /* Deletes all the words that we passed not the new word we just inputted. */
    @Query("DELETE FROM tbWords WHERE word IN(:words)")
    suspend fun deleteDictFromDB(words: List<String>)

    @Insert(onConflict = REPLACE)
    suspend fun insertDictToDB(word: List<WordDetailsEntity>)

}