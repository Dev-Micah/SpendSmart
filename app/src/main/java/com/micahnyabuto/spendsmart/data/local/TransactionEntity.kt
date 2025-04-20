package com.micahnyabuto.spendsmart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val category:String,
    val amount:Double,
    val date: Long

    )