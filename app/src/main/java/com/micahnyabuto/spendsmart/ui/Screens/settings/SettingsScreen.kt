package com.micahnyabuto.spendsmart.ui.Screens.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    modifier: Modifier= Modifier,
    navController :NavController
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
    Text("More features coming ")
    }
}