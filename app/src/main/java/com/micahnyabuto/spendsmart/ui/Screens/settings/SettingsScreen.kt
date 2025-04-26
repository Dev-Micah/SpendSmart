package com.micahnyabuto.spendsmart.ui.Screens.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micahnyabuto.spendsmart.ui.navigation.Destinations


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
){
    var isDarkMode by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Navigate back",
                        modifier= Modifier
                            .clickable{navController.navigate(Destinations.Home)}

                    )
                },
                title = {
                    Text("Settings",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp)
                }
            )
        }
    ){
        Surface (
            modifier= Modifier.fillMaxSize(),
            color =if(isDarkMode) Color.Black else Color.White
        ){
            Column {
                DarkModeRow(
                    isDarkMode = isDarkMode,
                    onDarkModeToggle = { isDarkMode = it },
                )
            }
        }

        }

    }
@Composable
fun DarkModeRow(
    isDarkMode: Boolean,
    onDarkModeToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Dark Mode",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Switch(
            checked = isDarkMode,
            onCheckedChange = onDarkModeToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Preview
@Composable
fun SettingScreenPreview(){
    SettingsScreen(
        modifier = Modifier,
        navController = rememberNavController(),
    )
}