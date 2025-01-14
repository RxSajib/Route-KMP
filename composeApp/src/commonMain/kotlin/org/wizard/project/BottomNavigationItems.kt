package org.wizard.project

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.af2905.jetpack_compose_navigation.screen.DetailScreen
import com.github.af2905.jetpack_compose_navigation.screen.FavoriteScreen
import com.github.af2905.jetpack_compose_navigation.screen.HomeScreen
import org.wizard.project.Routes.DETAIL_SCREEN
import org.wizard.project.Routes.FAVORITE_SCREEN
import org.wizard.project.Routes.HOME_SCREEN
import org.wizard.project.Routes.SPLASH_SCREEN
import org.wizard.project.screen.SplashScreen

data class Item(val route: String, val icon: ImageVector)

object Routes {
    const val HOME_SCREEN: String = "home_screen"
    const val FAVORITE_SCREEN: String = "favorite_screen"
    const val DETAIL_SCREEN: String = "detail_screen"
    const val SPLASH_SCREEN: String = "splash_screen"
}

@Composable
fun SetupNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        composable(route = HOME_SCREEN,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(200)
                )
            },
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(200)
                )
            }
            ) {
            HomeScreen(onItemClick = { navController.navigate(DETAIL_SCREEN) })
        }

        composable(route = SPLASH_SCREEN) {
            SplashScreen(navcontroller = navController)
        }

        composable(
            route = DETAIL_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(200)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(200)
                )
            }

        ) {
            DetailScreen()
        }
        composable(FAVORITE_SCREEN,
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(300)
                )
            },
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(300)
                )
            }
            ) {
            FavoriteScreen(
                navController
            )
        }
    }
}

@Composable
fun BottomNavigationItems(navController: NavController) {
    val itemList = listOf(
        Item(route = HOME_SCREEN, icon = Icons.Filled.Home),
        Item(route = FAVORITE_SCREEN, icon = Icons.Filled.Favorite)
    )

    val bottomNavigationItemRouteList = listOf(HOME_SCREEN, FAVORITE_SCREEN)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (navController.previousBackStackEntry != null &&
        !bottomNavigationItemRouteList.contains(currentRoute)
    ){
        null
    }else {
        if(currentRoute == Routes.SPLASH_SCREEN){

        }else {
            BottomNavigation {
                val destinationChanged: MutableState<String?> = remember { mutableStateOf(null) }
                val isInParentRoute = itemList.firstOrNull { it.route == destinationChanged.value } != null
                val parentRoute: MutableState<String?> =
                    remember(destinationChanged.value) { mutableStateOf(HOME_SCREEN) }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                fun selectedBottomNavigationItem() = if (isInParentRoute) {
                    parentRoute.value = currentRoute
                    currentRoute
                } else {
                    parentRoute.value
                }

                navController.addOnDestinationChangedListener { _, navDestination, _ ->
                    destinationChanged.value = navDestination.route
                }

                itemList.forEach { item ->
                    val isSelected = selectedBottomNavigationItem() == item.route
                    BottomNavigationItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = null) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Gray,
                        alwaysShowLabel = false,
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
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


}