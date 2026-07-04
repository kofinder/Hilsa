package com.finderbar.hilsa.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.finderbar.hilsa.core.ui.icons.AppIcons
import com.finderbar.hilsa.core.ui.layout.AppScreen
import com.finderbar.hilsa.feature.home.HomeScreen
import com.finderbar.hilsa.feature.pos.PosScreen
import com.finderbar.hilsa.feature.inventory.InventoryScreen
import com.finderbar.hilsa.feature.insight.InsightScreen
import com.finderbar.hilsa.feature.services.ServicesScreen
import com.finderbar.hilsa.navigation.Routes

@Composable
fun MainScreen(
    onLogout: () -> Unit
) {
    val navController = rememberNavController()
    
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Pos,
        BottomNavItem.Inventory,
        BottomNavItem.Insight,
        BottomNavItem.Services
    )

    AppScreen(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.HOME) {
                HomeScreen(onLogout = onLogout)
            }
            composable(Routes.POS) {
                PosScreen()
            }
            composable(Routes.INVENTORY) {
                InventoryScreen()
            }
            composable(Routes.INSIGHT) {
                InsightScreen()
            }
            composable(Routes.SERVICES) {
                ServicesScreen()
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    data object Home : BottomNavItem(Routes.HOME, "Home", AppIcons.Home)
    data object Pos : BottomNavItem(Routes.POS, "POS", Icons.Default.PointOfSale)
    data object Inventory : BottomNavItem(Routes.INVENTORY, "Inventory", AppIcons.Inventory)
    data object Insight : BottomNavItem(Routes.INSIGHT, "Insight", AppIcons.Insight)
    data object Services : BottomNavItem(Routes.SERVICES, "Services", AppIcons.More)
}
