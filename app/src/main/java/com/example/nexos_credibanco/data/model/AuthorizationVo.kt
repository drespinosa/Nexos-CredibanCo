package com.example.nexos_credibanco.data.model

import java.util.UUID

data class AuthorizationVo(
    var id: String = UUID.randomUUID().toString(),
    var commerceCode: String = "000123",
    var terminalCode: String = "000ABC",
    var amount: String = "",
    var card: String = "1234567890123456",
)
