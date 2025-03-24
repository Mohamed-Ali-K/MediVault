package com.meditrack.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.meditrack.ui.screens.dashboard.DashboardScreen
import com.meditrack.ui.screens.profile.ProfileScreen

/**
 * Sealed class representing the different screens in the app.
 * Each screen has a route for navigation, a title, and an icon for the bottom navigation.
 */
sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    /**
     * The dashboard/home screen showing an overview of medical information.
     */
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Filled.Home)
    
    /**
     * The medical records screen for viewing and managing health records.
     */
    object Records : Screen("records", "Records", Icons.Filled.MedicalServices)
    
    /**
     * The appointments screen for managing healthcare appointments.
     */
    object Appointments : Screen("appointments", "Appointments", Icons.Filled.DateRange)
    
    /**
     * The profile screen for managing user profiles.
     */
    object Profile : Screen("profile", "Profile", Icons.Filled.AccountCircle)
}

/**
 * Main composable for the MediTrack application.
 * 
 * This composable sets up the navigation architecture for the app, including the
 * bottom navigation bar and navigation host for screen transitions. It serves as
 * the container for all other screens in the application.
 */
@Composable
fun MediTrackApp() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Dashboard,
        Screen.Records,
        Screen.Appointments,
        Screen.Profile
    )
    
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen()
            }
            composable(Screen.Records.route) {
                // RecordsScreen will be implemented later
                Text("Records Screen")
            }
            composable(Screen.Appointments.route) {
                // AppointmentsScreen will be implemented later
                Text("Appointments Screen")
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
} 