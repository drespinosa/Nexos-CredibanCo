package com.example.nexos_credibanco.data.model

import com.google.gson.annotations.SerializedName

data class AuthorizationDTO(
    @SerializedName("id") val id: String,
    @SerializedName("commerceCode") val commerceCode: String,
    @SerializedName("terminalCode") val terminalCode: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("card") val card: String,
)
