package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.CancelDTO
import com.example.nexos_credibanco.data.model.CancelVo


open class CancelMapper() {

    /**
     * Realiza la conversión de un objeto CancelVo a un objeto CancelDTO.
     */
    fun businessToDto(business: CancelVo): CancelDTO {
        return CancelDTO(
            receiptId = business.receiptId,
            rrn = business.rrn,
        )
    }

}