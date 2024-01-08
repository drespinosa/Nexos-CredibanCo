package com.example.nexos_credibanco.core

import com.example.nexos.data.network.APIService
import com.example.nexos_credibanco.data.model.Constants.URL_API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private var retrofit: Retrofit? = null

    private fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getApiService(): APIService {
        return getRetrofitInstance().create(APIService::class.java)
    }

}
