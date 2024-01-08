package com.example.nexos_credibanco.data.model

data class AuthorizationResponseVo(
    val receiptId: String,
    val rrn: String,
    val statusCode: String,
    val statusDescription: String,
    val enable: Boolean,
)
