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
       Reports(
        label = "Reports",
        selectedIcon = R.drawable.ic_reports,
        unselectedIcon = R.drawable.ic_reports,
        route = Destinations.Home
    ),
       Profile(
        label = "Profile",
        selectedIcon = R.drawable.ic_account,
        unselectedIcon = R.drawable.ic_account,
        route = Destinations.Home
    ),


    Settings(
        label = "Settings",
        selectedIcon = R.drawable.ic_settings,
        unselectedIcon = R.drawable.ic_settings,
        route = Destinations.Settings
    ),

}