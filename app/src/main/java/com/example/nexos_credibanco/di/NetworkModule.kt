package com.example.nexos_credibanco.di

import android.content.Context
import androidx.room.Room
import com.example.nexos_credibanco.data.model.Constants.URL_API
import com.example.nexos_credibanco.data.network.APIService
import com.example.nexos_credibanco.database.TransactionDatabase
import com.example.nexos_credibanco.repository.TransactionRepository
import com.example.nexos_credibanco.repository.implementation.IRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }


    @Provides
    @Singleton
    fun provideIRepository(repository: TransactionRepository): IRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context): TransactionDatabase {
        return Room.databaseBuilder(
            context,
            TransactionDatabase::class.java,
            "transaction_table"
        ).build()
    }

}