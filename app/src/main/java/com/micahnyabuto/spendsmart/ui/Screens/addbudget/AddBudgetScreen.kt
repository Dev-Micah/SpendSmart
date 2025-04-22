package com.micahnyabuto.spendsmart.ui.Screens.addbudget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AddBudgetItemScreen (
    modifier: Modifier =Modifier,
    navController: NavController
) {

    Box (
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("Add budgetScreen")
    }


}