package org.wizard.project.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.wizard.project.Routes.HOME_SCREEN
import org.wizard.project.Routes.SPLASH_SCREEN

@Composable
fun SplashScreen(navcontroller : NavHostController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()

        val scope = rememberCoroutineScope()
        scope.launch {
            delay(3000)
            navcontroller.navigate(HOME_SCREEN){
                popUpTo(SPLASH_SCREEN){
                    inclusive = true
                }
            }
        }
    }
}