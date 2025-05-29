package edu.com.reyphillnuez_ap2_p1.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.com.reyphillnuez_ap2_p1.Presentation.Sistema.List

@Composable
fun HostNavigation(
    navHostController: NavHostController,

    ) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            List()
        }
    }
}
