package com.example.nexos_credibanco.repository.implementation

import com.example.nexos_credibanco.data.model.AuthorizationResponseVo
import com.example.nexos_credibanco.data.model.AuthorizationVo
import com.example.nexos_credibanco.data.model.CancelResponseVo
import com.example.nexos_credibanco.data.model.CancelVo


interface IRepository {

    /**
     * Método que autoriza la transacción
     */
    suspend fun authorizeTransaction(authorizationCode: String, authorizationVo: AuthorizationVo): AuthorizationResponseVo

    /**
     * Método que anula la transacción
     */
    suspend fun cancelTransaction(authorizationCode: String, cancelVo: CancelVo): CancelResponseVo

    /**
     * Método que obtiene las transacciones
     */
    suspend fun getTransactions(): List<AuthorizationResponseVo>

}