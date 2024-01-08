package com.example.nexos.data.network

import com.example.nexos_credibanco.data.model.CancelDTO
import com.example.nexos_credibanco.data.model.CancelResponseDTO
import com.example.nexos_credibanco.data.model.AuthorizationDTO
import com.example.nexos_credibanco.data.model.AuthorizationResponseDTO
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface APIService {

    @POST("authorization")
    suspend fun authorize(
        @Header("Authorization") authorizationCode: String,
        @Body authorization: AuthorizationDTO
    ): AuthorizationResponseDTO

    @POST("annulment")
    suspend fun cancel(
        @Header("Authorization") authorizationCode: String,
        @Body cancel: CancelDTO
    ): CancelResponseDTO

}