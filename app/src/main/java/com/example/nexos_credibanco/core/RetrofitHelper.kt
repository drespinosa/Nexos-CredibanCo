package com.example.nexos_credibanco.core

import com.example.nexos_credibanco.data.model.Constants.URL_API
import com.example.nexos_credibanco.data.network.APIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private var retrofit: Retrofit? = null

    /**
     * Proporciona una instancia de Retrofit
     */
    private fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    /**
     * Proporciona el acceso a una instancia de la interfaz APIService
     */
    fun getApiService(): APIService {
        return getRetrofitInstance().create(APIService::class.java)
    }

}
