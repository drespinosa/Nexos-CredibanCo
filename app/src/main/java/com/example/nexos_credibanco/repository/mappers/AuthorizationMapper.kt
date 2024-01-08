package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.AuthorizationDTO
import com.example.nexos_credibanco.data.model.AuthorizationVo


open class AuthorizationMapper() {

    fun businessToDto(business: AuthorizationVo): AuthorizationDTO {
        return AuthorizationDTO(
            id = business.id,
            commerceCode = business.commerceCode,
            terminalCode = business.terminalCode,
            amount = business.amount,
            card = business.card,
        )
    }

    fun dtoToBusiness(dto: AuthorizationDTO): AuthorizationVo {
        return AuthorizationVo(
            id = dto.id,
            commerceCode = dto.commerceCode,
            terminalCode = dto.terminalCode,
            amount = dto.amount,
            card = dto.card,
        )
    }

}