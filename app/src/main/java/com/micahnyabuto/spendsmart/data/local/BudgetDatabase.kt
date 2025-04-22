package com.micahnyabuto.spendsmart.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[TransactionEntity::class,BudgetEntity::class], version =1)
abstract class BudgetDatabase : RoomDatabase(){
    abstract fun budgetDao(): BudgetDao
    abstract fun transactionDao(): TransactionDao
}