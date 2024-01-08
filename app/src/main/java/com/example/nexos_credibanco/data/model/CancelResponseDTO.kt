package com.example.nexos_credibanco.data.model

import com.google.gson.annotations.SerializedName

data class CancelResponseDTO(
    @SerializedName("statusCode") val statusCode: String,
    @SerializedName("statusDescription") val statusDescription: String,
)
