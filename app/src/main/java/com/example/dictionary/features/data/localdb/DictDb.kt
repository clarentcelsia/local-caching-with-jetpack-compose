package com.example.dictionary.features.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictionary.features.data.localdb.dao.DictDao
import com.example.dictionary.features.data.localdb.model.WordDetailsEntity

@Database(
    entities = [WordDetailsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class DictDb : RoomDatabase(){

    abstract val dictDao: DictDao
}