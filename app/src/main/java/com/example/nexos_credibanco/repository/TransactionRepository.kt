package com.example.nexos_credibanco.repository

import android.content.Context
import com.example.nexos_credibanco.data.model.AuthorizationResponseVo
import com.example.nexos_credibanco.data.model.AuthorizationVo
import com.example.nexos_credibanco.data.model.CancelResponseVo
import com.example.nexos_credibanco.data.model.CancelVo
import com.example.nexos_credibanco.data.network.APIService
import com.example.nexos_credibanco.database.TransactionDatabase
import com.example.nexos_credibanco.repository.implementation.IRepository
import com.example.nexos_credibanco.repository.mappers.AuthorizationMapper
import com.example.nexos_credibanco.repository.mappers.AuthorizationResponseMapper
import com.example.nexos_credibanco.repository.mappers.CancelMapper
import com.example.nexos_credibanco.repository.mappers.CancelResponseMapper
import com.example.nexos_credibanco.repository.mappers.TransactionDBMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class TransactionRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val apiService: APIService,
    private val roomDatabase: TransactionDatabase
) : IRepository {

    override suspend fun authorizeTransaction(authorizationCode: String, authorizationVo: AuthorizationVo): AuthorizationResponseVo {
        val dto = AuthorizationMapper().businessToDto(authorizationVo)
        val responseApi = AuthorizationResponseMapper().dtoToBusiness(apiService.authorize(authorizationCode, dto))
        roomDatabase.getTransactionDao().insertTransaction(TransactionDBMapper().businessToDto(responseApi))
        return responseApi
    }

    override suspend fun cancelTransaction(authorizationCode: String, cancelVo: CancelVo): CancelResponseVo {
        val dto = CancelMapper().businessToDto(cancelVo)
        val responseApi = CancelResponseMapper().dtoToBusiness(apiService.cancel(authorizationCode, dto))
        roomDatabase.getTransactionDao().updateColumnName(dto.receiptId, false)
        return responseApi
    }

    override suspend fun getTransactions(): List<AuthorizationResponseVo> {
        return TransactionDBMapper().dtoToBusiness(roomDatabase.getTransactionDao().getAllTransactions())
    }

}