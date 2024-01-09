package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.CancelResponseDTO
import com.example.nexos_credibanco.data.model.CancelResponseVo


open class CancelResponseMapper() {

    /**
     * Realiza la conversión de un objeto CancelResponseDTO a un objeto CancelResponseVo.
     */
    fun dtoToBusiness(dto: CancelResponseDTO): CancelResponseVo {
        return CancelResponseVo(
            statusCode = dto.statusCode,
            statusDescription = dto.statusDescription,
        )
    }

}