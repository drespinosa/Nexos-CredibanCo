package com.example.nexos_credibanco.repository.mappers

import com.example.nexos_credibanco.data.model.AuthorizationDTO
import com.example.nexos_credibanco.data.model.AuthorizationVo


open class AuthorizationMapper() {

    /**
     * Realiza la conversión de un objeto AuthorizationVo a un objeto AuthorizationDTO.
     */
    fun businessToDto(business: AuthorizationVo): AuthorizationDTO {
        return AuthorizationDTO(
            id = business.id,
            commerceCode = business.commerceCode,
            terminalCode = business.terminalCode,
            amount = business.amount,
            card = business.card,
        )
    }

}