package com.micahnyabuto.spendsmart.ui.Screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.micahnyabuto.spendsmart.ui.navigation.Destinations

@Composable
fun SettingsScreen(
    modifier: Modifier= Modifier,
    navController: NavController
){
    var isDarkMode by remember { mutableStateOf(false) }
    var isDarkModeToggle by remember { mutableStateOf(false) }

    SettingsScreenContent(
        modifier =modifier,
        isDarkMode = isDarkMode,
        isDarkModeToggle = isDarkModeToggle,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean,
    isDarkModeToggle: Boolean,
    navController: NavController,
){

    Scaffold(
        topBar = {
           TopAppBar(
               title = {
                Text("Settings",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                }

           )


        }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Create or sign in to your account for improved security of your budgets",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Button(
                        shape = RoundedCornerShape(8.dp),
                        onClick = {navController.navigate(Destinations.Profile)}
                    ) {
                        Text(text = "Get started")
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Enable Dark Theme")
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = {isDarkModeToggle}
                )
            }
            }

        }
}

