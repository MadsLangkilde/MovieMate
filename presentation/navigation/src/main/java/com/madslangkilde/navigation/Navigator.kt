package com.madslangkilde.navigation

import androidx.navigation.NavHostController

class Navigator(private val navController: NavHostController) {
    fun navigateTo(route: String) {
        if (navController.currentDestination?.route == route) {
            return
        }
        if (route == NavigationRoute.START) {
            navController.navigate(route) {
                popUpTo(NavigationRoute.START) { inclusive = false }
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}
