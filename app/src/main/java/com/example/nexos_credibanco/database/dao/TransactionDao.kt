package com.example.nexos_credibanco.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nexos_credibanco.database.entities.TransactionEntity

@Dao
interface TransactionDao{

    @Query("SELECT * FROM transaction_table")
    suspend fun getAllTransactions(): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("UPDATE transaction_table SET status = :newValue WHERE receiptId = :receiptId")
    suspend fun updateColumnName(receiptId: String, newValue: Boolean)


}