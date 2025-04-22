package com.micahnyabuto.spendsmart.di

import android.content.Context
import androidx.room.Room
import com.micahnyabuto.spendsmart.data.local.BudgetDao
import com.micahnyabuto.spendsmart.data.local.BudgetDatabase
import com.micahnyabuto.spendsmart.data.local.TransactionDao
import com.micahnyabuto.spendsmart.data.repository.BudgetRepository
import com.micahnyabuto.spendsmart.data.repository.BudgetRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBudgetDatabase(
        @ApplicationContext context: Context
    ): BudgetDatabase{
        return Room.databaseBuilder(
            context,
            BudgetDatabase::class.java,
            "spend_smart_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBudgetDao(database: BudgetDatabase) : BudgetDao{
        return database.budgetDao()

    }
    @Singleton
    @Provides
    fun provideTransactionDao(database: BudgetDatabase) : TransactionDao{
        return database.transactionDao()
    }

    @Singleton
    @Provides
    fun provideBudgetRepository(
        transactionDao: TransactionDao,
        budgetDao: BudgetDao
    ): BudgetRepository{
        return BudgetRepositoryImpl(
            transactionDao =transactionDao,
            budgetDao = budgetDao
        )
    }

}