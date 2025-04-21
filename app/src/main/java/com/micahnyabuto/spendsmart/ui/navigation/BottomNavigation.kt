package com.micahnyabuto.spendsmart.ui.navigation

import com.micahnyabuto.spendsmart.R


enum class BottomNavigation(
    val label: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val route : Any
){
    Home(
        label = "Home",
        selectedIcon = R.drawable.ic_home,
        unselectedIcon = R.drawable.ic_home,
        route = Destinations.Home
    ),
    Settings(
        label = "Settings",
        selectedIcon = R.drawable.ic_settings,
        unselectedIcon = R.drawable.ic_settings,
        route = Destinations.Settings
    )
}