package edu.com.reyphillnuez_ap2_p1.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.com.reyphillnuez_ap2_p1.Presentation.Sistema.TareasList
import edu.com.reyphillnuez_ap2_p1.Presentation.Sistema.TareasScreen

@Composable
fun HostNavigation(
    navHostController: NavHostController,

    ) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            TareasList(
                createTarea = {
                    navHostController.navigate(Screen.Tarea(0))
                },
                onEdit = { tareaId ->
                    navHostController.navigate(Screen.Tarea(tareaId))
                }
            )
        }

        composable<Screen.Tarea> { backStack ->
            val tareaId = backStack.toRoute<Screen.Tarea>().id
            TareasScreen(
                tareaId = tareaId,
                goBack = {
                    navHostController.navigateUp()
                }
            )

        }
    }
}
