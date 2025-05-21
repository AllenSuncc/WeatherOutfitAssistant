package com.example.myapplication.navigation

import android.app.Activity
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.screen.History
import com.example.myapplication.viewmodel.UserViewModel
import com.example.myapplication.screen.Login
import com.example.myapplication.screen.Register
import com.example.myapplication.screen.Report
import com.example.myapplicaiton.screen.Home
import com.example.myapplication.screen.Record



data class NavRoute(val route: String, val icon: ImageVector, val label: String)

@Composable
fun BottomNavigationBar() {
    val navRoutes = listOf(
        NavRoute("home", Icons.Filled.Home, "Home"),
        NavRoute("record", Icons.Filled.Create, "Record"),
        NavRoute("history", Icons.Filled.DateRange, "History"),
        NavRoute("Report", Icons.Filled.AccountCircle, "Report")
    )

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar(navController = navController)) {
                BottomNavigation(
                    modifier = Modifier.padding(bottom = 20.dp),
                    backgroundColor = Color(0xFFFFFFE0)
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    navRoutes.forEach { navRoute ->
                        BottomNavigationItem(
                            icon = { Icon(navRoute.icon, contentDescription = navRoute.label) },
                            label = { Text(navRoute.label) },
                            selected = currentDestination?.route == navRoute.route,
                            onClick = {
                                navController.navigate(navRoute.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        inclusive = false
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("login") {
                val userViewModel: UserViewModel = viewModel()
                Login(navController, userViewModel)
            }
            composable("register") {
                val userViewModel: UserViewModel = viewModel()
                Register(navController, userViewModel)
            }
            composable("home") { Home() }
            composable("record") { Record() }
            composable("history") { History() }
            composable("report") { Report() }
        }
    }
}

/**
 * Determine whether the current page should display the bottom navigation bar (login page not displayed)
 */
@Composable
fun shouldShowBottomBar(navController: NavHostController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    return currentRoute !in listOf("login", "register")
}