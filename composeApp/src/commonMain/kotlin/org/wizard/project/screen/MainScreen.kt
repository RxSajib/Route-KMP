package com.github.af2905.jetpack_compose_navigation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.wizard.project.BottomNavigationItems
import org.wizard.project.Routes.FAVORITE_SCREEN
import org.wizard.project.Routes.HOME_SCREEN
import org.wizard.project.SetupNavigationHost
import org.wizard.project.TopBarNavigation

@Composable
fun MainScreen(
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarNavigation(navController = navController) },

        bottomBar = { BottomNavigationItems(navController = navController) }
    ) { padding ->
        SetupNavigationHost(
            navController = navController,
            modifier = Modifier.padding(padding)
        )
    }
}