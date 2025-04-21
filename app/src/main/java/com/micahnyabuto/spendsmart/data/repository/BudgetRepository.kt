package com.micahnyabuto.spendsmart.data.repository


import com.micahnyabuto.spendsmart.data.local.BudgetDao
import com.micahnyabuto.spendsmart.data.local.BudgetEntity
import com.micahnyabuto.spendsmart.data.local.TransactionDao
import com.micahnyabuto.spendsmart.data.local.TransactionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface BudgetRepository {
    val allTransactions: Flow<List<TransactionEntity>>
    suspend fun addTransaction(transaction: TransactionEntity)
    suspend fun addBudget(budget: BudgetEntity)
    fun getBudget(): Flow<BudgetEntity?>
    fun getRemainingBudget(): Flow<Double>
}
class BudgetRepositoryImpl(
    private val transactionDao: TransactionDao,
    private val budgetDao: BudgetDao
) : BudgetRepository {
    override val allTransactions: Flow<List<TransactionEntity>> =
        transactionDao.getAllTransactions()

    override suspend fun addTransaction(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
        budgetDao.updateSpentAmount(transaction.amount)
    }

    override suspend fun addBudget(budget: BudgetEntity) {
        budgetDao.insertBudget(budget)
   }

    override fun getBudget(): Flow<BudgetEntity?> {
        return budgetDao.getTotalBudget()
    }

    override fun getRemainingBudget(): Flow<Double> {
        return budgetDao.getTotalBudget().map{
            it.allocatedAmount - it.spentAmount
        }
    }
}