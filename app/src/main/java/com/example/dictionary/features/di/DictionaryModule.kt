package com.example.dictionary.features.di

import android.content.Context
import androidx.room.Room
import com.example.dictionary.features.data.localdb.Converter
import com.example.dictionary.features.data.localdb.DictDb
import com.example.dictionary.features.data.remote.DictionaryAPI
import com.example.dictionary.features.data.remote.DictionaryAPI.Companion.BASE_URL
import com.example.dictionary.features.data.repository.DictRepoImpl
import com.example.dictionary.features.data.util.GsonParser
import com.example.dictionary.features.domain.repository.DictRepo
import com.example.dictionary.features.domain.use_case.GetWordDetails
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Which dependency we need to inject
@Module
@InstallIn(SingletonComponent::class)
class DictionaryModule {

    @Singleton
    @Provides
    fun provideGetWordDetailsUseCase(
        repo: DictRepo
    ): GetWordDetails {
        return GetWordDetails(repo)
    }

    @Singleton
    @Provides
    fun provideDictRepo(
        api: DictionaryAPI,
        db: DictDb
    ): DictRepo {
        return DictRepoImpl(api, db.dictDao)
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): DictDb = Room.databaseBuilder(
        context,
        DictDb::class.java,
        "dbDictionary"
    ).addTypeConverter(Converter(GsonParser(Gson())))
        .build()

    @Singleton
    @Provides
    fun provideApi(): DictionaryAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryAPI::class.java)
    }

}