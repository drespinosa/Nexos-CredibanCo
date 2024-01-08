package com.example.nexos_credibanco.data.model

import com.google.gson.annotations.SerializedName

data class CancelDTO(
    @SerializedName("receiptId") val receiptId: String,
    @SerializedName("rrn") val rrn: String,
)
