package com.example.nexos_credibanco.repository.implementation

import com.example.nexos_credibanco.data.model.AuthorizationResponseVo
import com.example.nexos_credibanco.data.model.AuthorizationVo
import com.example.nexos_credibanco.data.model.CancelResponseVo
import com.example.nexos_credibanco.data.model.CancelVo


interface IRepository {

    suspend fun authorizeTransaction(authorizationCode: String, authorizationVo: AuthorizationVo): AuthorizationResponseVo
    suspend fun cancelTransaction(authorizationCode: String, cancelVo: CancelVo): CancelResponseVo
    suspend fun getTransactions(): List<AuthorizationResponseVo>

}