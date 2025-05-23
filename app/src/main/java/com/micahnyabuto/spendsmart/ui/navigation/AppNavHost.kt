package com.micahnyabuto.spendsmart.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.micahnyabuto.spendsmart.ui.Screens.addbudget.AddBudgetItemScreen
import com.micahnyabuto.spendsmart.ui.Screens.addbudget.BudgetAddedScreen
import com.micahnyabuto.spendsmart.ui.Screens.home.HomeScreen
import com.micahnyabuto.spendsmart.ui.Screens.profile.ProfileScreen
import com.micahnyabuto.spendsmart.ui.Screens.reports.ReportsScreen
import com.micahnyabuto.spendsmart.ui.Screens.settings.SettingsScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier= Modifier
){
    NavHost(
        modifier =modifier,
        navController =navController,
        startDestination = Destinations.Home
    ){
        composable<Destinations.Home> {
            HomeScreen(
                navController = navController
            )
        }

       composable<Destinations.Reports> {
            ReportsScreen(
              navController =navController
          )
       }
        composable<Destinations.Profile> {
            ProfileScreen(
              navController =navController
          )
       }
        composable<Destinations.Settings> {
            SettingsScreen(
                navController = navController
            )
        }
        composable <Destinations.AddBudgetItem>{
            AddBudgetItemScreen(
                navController =navController
            )
        }
        composable<Destinations.BudgetAdded> { backStackEntry ->
            val budgetItemName = backStackEntry.toRoute<Destinations.BudgetAdded>().budgetItemName
            val amount =backStackEntry.toRoute<Destinations.BudgetAdded>().amount
            val date =backStackEntry.toRoute<Destinations.BudgetAdded>().date
            BudgetAddedScreen(
                budgetItemName =budgetItemName,
                amount = amount,
                date = date,
                navController = navController,
                modifier = modifier
            )
        }
    }

}