package com.micahnyabuto.spendsmart.ui.navigation

import kotlinx.serialization.Serializable

sealed class Destinations {
    @Serializable
    object Home

    @Serializable
    object Settings

    @Serializable
    object AddBudgetItem

    @Serializable
    object Profile

    @Serializable
    object Reports


    @Serializable
    data class BudgetAdded(

        val budgetItemName: String,
        val amount: String,
        val date : String
    )
}