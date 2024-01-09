package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.AuthorizationResponseVo
import com.example.nexos_credibanco.data.model.CancelVo
import com.example.nexos_credibanco.database.entities.TransactionEntity


open class TransactionDBMapper() {

    /**
     * Realiza la conversión de un objeto AuthorizationResponseVo a un objeto TransactionEntity.
     */
    fun businessToDto(business: AuthorizationResponseVo): TransactionEntity {
        return TransactionEntity(
            receiptId = business.receiptId,
            rrn = business.rrn,
            statusCode = business.statusCode,
            statusDescription = business.statusDescription,
            status = business.enable
        )
    }

    /**
     * Realiza la conversión de una lista de objetos TransactionEntity a una lista de objetos AuthorizationResponseVo.
     */
    fun dtoToBusiness(dto: List<TransactionEntity>): List<AuthorizationResponseVo> {
        return dto.map {
            AuthorizationResponseVo(
                receiptId = it.receiptId,
                rrn = it.rrn,
                statusCode = it.statusCode,
                statusDescription = it.statusDescription,
                enable = it.status
            )
        }
    }

}