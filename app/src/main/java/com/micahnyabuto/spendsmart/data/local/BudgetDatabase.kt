package com.micahnyabuto.spendsmart.data.local

import androidx.room.Database

@Database(entities =[TransactionEntity::class,BudgetEntity::class], version =1)
abstract class BudgetDatabase {
    abstract fun budgetDao(): BudgetDao
    abstract fun transactionDao(): TransactionDao
}