package com.micahnyabuto.spendsmart.ui.Screens.addbudget

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micahnyabuto.spendsmart.data.local.TransactionEntity
import com.micahnyabuto.spendsmart.data.repository.BudgetRepository
import com.micahnyabuto.spendsmart.utils.timestampToDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.temporal.TemporalAmount
import javax.inject.Inject

@HiltViewModel
class AddBudgetViewModel @Inject constructor(
    private val repository: BudgetRepository
): ViewModel(){
    private val _eventFlow = Channel<  AddBudgetEvent>(Channel.UNLIMITED)
    val eventFlow = _eventFlow.receiveAsFlow()

    fun addTransaction(
        selectedCategory: String,
        itemName: String,
        allocationAmount: String
    ) {

        viewModelScope.launch {
            if (selectedCategory.isBlank()|| itemName.isBlank()|| allocationAmount.isBlank()){
                _eventFlow.trySend(AddBudgetEvent.SnackbarEvent("All fields are required"))
                return@launch
            }

            val transaction = TransactionEntity(
                category = selectedCategory,
                amount = allocationAmount.toDouble(),
                name = itemName,
                date = System.currentTimeMillis()
            )

            repository.addTransaction(transaction)

            _eventFlow.trySend(
                AddBudgetEvent.NavigateSuccessScreen(
                    itemName= itemName,
                    amount = allocationAmount,
                    date = transaction.date.timestampToDateTime()
                )
            )
        }

    }
}

sealed class AddBudgetEvent {
    data class NavigateSuccessScreen(
        val itemName: String,
        val amount: String,
        val date: String
    ) : AddBudgetEvent()

    data class SnackbarEvent(val message: String) : AddBudgetEvent()
}