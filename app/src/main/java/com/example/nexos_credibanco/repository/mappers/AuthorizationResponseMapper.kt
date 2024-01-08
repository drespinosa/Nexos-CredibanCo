package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.AuthorizationResponseDTO
import com.example.nexos_credibanco.data.model.AuthorizationResponseVo


open class AuthorizationResponseMapper() {

    fun businessToDto(business: AuthorizationResponseVo): AuthorizationResponseDTO {
        return AuthorizationResponseDTO(
            receiptId = business.receiptId,
            rrn = business.rrn,
            statusCode = business.statusCode,
            statusDescription = business.statusDescription,
        )
    }

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