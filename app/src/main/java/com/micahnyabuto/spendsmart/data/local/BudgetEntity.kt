package com.micahnyabuto.spendsmart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budgets")
data class BudgetEntity(
    @PrimaryKey
    val allocatedAmount: Double,  /*Total amount allocated*/
    val spentAmount: Double  /*Amount spent*/
)
