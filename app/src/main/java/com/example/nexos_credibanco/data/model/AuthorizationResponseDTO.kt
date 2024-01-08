package com.example.nexos_credibanco.data.model

import com.google.gson.annotations.SerializedName

data class AuthorizationResponseDTO(
    @SerializedName("receiptId") val receiptId: String,
    @SerializedName("rrn") val rrn: String,
    @SerializedName("statusCode") val statusCode: String,
    @SerializedName("statusDescription") val statusDescription: String,
)
