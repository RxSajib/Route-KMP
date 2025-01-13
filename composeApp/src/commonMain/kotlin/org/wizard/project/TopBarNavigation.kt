package org.wizard.project

import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.stringResource
import org.wizard.project.Routes.DETAIL_SCREEN
import org.wizard.project.Routes.FAVORITE_SCREEN
import org.wizard.project.Routes.HOME_SCREEN

@Composable
fun TopBarNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavigationItemRouteList = listOf(HOME_SCREEN, FAVORITE_SCREEN)

    val title = when (currentRoute) {
        HOME_SCREEN -> "Home"
        FAVORITE_SCREEN -> "Fav"
        DETAIL_SCREEN -> "Details"
        else -> ""
    }

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                color = Color.Black
            )
        },
        backgroundColor = Color.White,
        navigationIcon =
        if (navController.previousBackStackEntry != null &&
            !bottomNavigationItemRouteList.contains(currentRoute)
        ) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
            }
        } else {
            null
        }
    )
}
