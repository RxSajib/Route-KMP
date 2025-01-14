package com.github.af2905.jetpack_compose_navigation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.wizard.project.Routes.DETAIL_SCREEN

@Composable
fun FavoriteScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Favorite Screen",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .clickable {
                    navController.navigate(DETAIL_SCREEN)
                }
        )
    }
}