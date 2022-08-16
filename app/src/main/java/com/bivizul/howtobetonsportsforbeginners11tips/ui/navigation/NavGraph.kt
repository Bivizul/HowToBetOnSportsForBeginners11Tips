package com.bivizul.howtobetonsportsforbeginners11tips.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bivizul.howtobetonsportsforbeginners11tips.data.getSetRes
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.SetRes
import com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main.ContentsViewModel
import com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main.MainRoute
import com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.predmain.PredmainRoute
import com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.predmain.PredmainViewModel

object Route{
    const val PREDMAIN = "predmain_route"
    const val MAIN = "main_route"
}

//@Inject
//lateinit var factory: PredmainViewModelFactory.Factory

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination:String = Route.PREDMAIN
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = startDestination, builder = {
        composable(route = Route.PREDMAIN){
            val viewModel = hiltViewModel<PredmainViewModel>()
            viewModel.getResContents(SetRes(getSetRes(context)))
            PredmainRoute(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(route = Route.MAIN){
            val viewModel = hiltViewModel<ContentsViewModel>()
            MainRoute(viewModel = viewModel)

        }

    })

}