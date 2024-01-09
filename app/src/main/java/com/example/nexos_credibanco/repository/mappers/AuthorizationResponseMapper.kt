package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.AuthorizationResponseDTO
import com.example.nexos_credibanco.data.model.AuthorizationResponseVo


open class AuthorizationResponseMapper() {

    /**
     * Realiza la conversión de un objeto AuthorizationResponseDTO a un objeto AuthorizationResponseVo.
     */
    fun dtoToBusiness(dto: AuthorizationResponseDTO): AuthorizationResponseVo {
        return AuthorizationResponseVo(
            receiptId = dto.receiptId,
            rrn = dto.rrn,
            statusCode = dto.statusCode,
            statusDescription = dto.statusDescription,
            enable = dto.statusCode == "00"
        )
    }

}