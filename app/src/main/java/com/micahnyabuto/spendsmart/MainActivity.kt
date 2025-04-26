package com.micahnyabuto.spendsmart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.micahnyabuto.spendsmart.ui.navigation.AppNavHost
import com.micahnyabuto.spendsmart.ui.navigation.BottomNavigation
import com.micahnyabuto.spendsmart.ui.navigation.Destinations
import com.micahnyabuto.spendsmart.ui.theme.SpendSmartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableInteractionSource", "RememberReturnType",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendSmartTheme {
                val navController =rememberNavController()

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: Destinations.Home::class.qualifiedName.orEmpty()
                val showBottomNavigation =true

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButtonPosition = FabPosition.Center,
                    contentWindowInsets = WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal),
                    floatingActionButton = {
                        FloatingActionButton(
                            modifier = Modifier
                                .offset(y =72.dp)
                                .size(45.dp),
                            onClick = {navController.navigate(Destinations.AddBudgetItem)},
                            shape = RoundedCornerShape(60.dp),
                            containerColor = MaterialTheme.colorScheme.primary

                            ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add budget item")
                        }

                    },
                    bottomBar = {
                        if (showBottomNavigation) {
                            Column {
                                HorizontalDivider(thickness = 4.dp)
                                NavigationBar(
                                    tonalElevation = 0.dp,
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                                ) {
                                    BottomNavigation.entries.forEachIndexed { index, navigationItem ->
                                        val isSelected by remember(currentRoute) {
                                            derivedStateOf { currentRoute == navigationItem.route::class.qualifiedName }
                                        }

                                        NavigationBarItem(
                                            selected = isSelected,
                                            icon = {
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                    verticalArrangement = Arrangement.Center,
                                                    modifier = Modifier.padding(vertical = 8.dp)
                                                ) {
                                                    Icon(
                                                        modifier = Modifier.size(24.dp)

                                                        ,
                                                        painter = painterResource(
                                                            if (isSelected) navigationItem.selectedIcon
                                                            else navigationItem.unselectedIcon
                                                        ),
                                                        contentDescription = navigationItem.label
                                                    )
                                                    Spacer(modifier = Modifier.height(4.dp))
                                                    Text(
                                                        text = navigationItem.label,
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            fontSize = 10.sp,
                                                            fontWeight = if (isSelected) FontWeight.SemiBold
                                                            else FontWeight.Normal
                                                        )
                                                    )
                                                }
                                            },
                                                onClick = {
                                                    navController.navigate(navigationItem.route)
                                                },
                                                colors = NavigationBarItemDefaults.colors(
                                                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                        elevation = 0.dp
                                                    ),
                                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                                                    unselectedTextColor = MaterialTheme.colorScheme.onSurface
                                                )
                                            )
                                        }
                                }
                            }
                        }
                    }
                )

                {
                AppNavHost(
                    modifier = Modifier
                        .fillMaxSize(),
                    navController = navController
                )

            }
        }
    }
    }
}

