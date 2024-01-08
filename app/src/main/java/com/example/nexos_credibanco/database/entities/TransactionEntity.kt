package com.example.nexos_credibanco.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "receiptId") val receiptId: String,
    @ColumnInfo(name = "rrn") val rrn: String,
    @ColumnInfo(name = "statusCode") val statusCode: String,
    @ColumnInfo(name = "statusDescription") val statusDescription: String,
    @ColumnInfo(name = "status") val status: Boolean,
)