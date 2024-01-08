package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.CancelDTO
import com.example.nexos_credibanco.data.model.CancelVo


open class CancelMapper() {

    fun businessToDto(business: CancelVo): CancelDTO {
        return CancelDTO(
            receiptId = business.receiptId,
            rrn = business.rrn,
        )
    }

    fun dtoToBusiness(dto: CancelDTO): CancelVo {
        return CancelVo(
            receiptId = dto.receiptId,
            rrn = dto.rrn,
        )
    }

}