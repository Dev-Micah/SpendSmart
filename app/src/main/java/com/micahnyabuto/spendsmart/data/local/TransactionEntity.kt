package com.micahnyabuto.spendsmart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val category:String,
    val amount:Double,
    val date: Long

    )