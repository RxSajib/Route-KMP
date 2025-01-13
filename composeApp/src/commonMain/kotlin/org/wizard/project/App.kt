package org.wizard.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.af2905.jetpack_compose_navigation.screen.MainScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import routekmp.composeapp.generated.resources.Res
import routekmp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val navController: NavHostController = rememberNavController()

    MaterialTheme {
        MainScreen(
            scaffoldState = scaffoldState,
            navController = navController
        )
    }
}