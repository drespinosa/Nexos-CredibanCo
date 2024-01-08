package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.CancelResponseDTO
import com.example.nexos_credibanco.data.model.CancelResponseVo


open class CancelResponseMapper() {

    fun businessToDto(business: CancelResponseVo): CancelResponseDTO {
        return CancelResponseDTO(
            statusCode = business.statusCode,
            statusDescription = business.statusDescription,
        )
    }

    fun dtoToBusiness(dto: CancelResponseDTO): CancelResponseVo {
        return CancelResponseVo(
            statusCode = dto.statusCode,
            statusDescription = dto.statusDescription,
        )
    }

}