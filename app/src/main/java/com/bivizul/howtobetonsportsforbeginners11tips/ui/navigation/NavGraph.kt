package com.bivizul.howtobetonsportsforbeginners11tips.ui.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bivizul.howtobetonsportsforbeginners11tips.data.getSetRes
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.SetRes
import com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main.MainRoute
import com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main.MainViewModel
import com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.predmain.PredmainRoute
import com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.predmain.PredmainViewModel

object Route {
    const val PREDMAIN = "predmain_route"
    const val MAIN = "main_route"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.PREDMAIN,
    predmainViewModel: PredmainViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    predmainViewModel.getResContents(SetRes(getSetRes(context)))

    NavHost(navController = navController, startDestination = startDestination, builder = {
        composable(route = Route.PREDMAIN) {
            PredmainRoute(
                navController = navController,
                viewModel = predmainViewModel
            )
            BackHandler {
                activity.finishAndRemoveTask()
                System.exit(0)
            }
        }

        composable(route = Route.MAIN) {
            MainRoute(viewModel = mainViewModel)
            BackHandler {
                activity.finishAndRemoveTask()
                System.exit(0)
            }
        }
    })
}