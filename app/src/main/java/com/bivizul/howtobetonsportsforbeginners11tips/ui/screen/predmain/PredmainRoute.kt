package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.predmain

import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.navigation.NavController
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.EMPTY_STRING
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.LB2
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.PB2
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ResContents
import com.bivizul.howtobetonsportsforbeginners11tips.ui.navigation.Route
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PredmainRoute(
    navController: NavController,
    viewModel: PredmainViewModel,
) {

    Log.e("qwer","PredmainRoute")

    val resContents by viewModel.resContents.collectAsState(initial = ResContents(EMPTY_STRING))

    if (resContents.resContents != EMPTY_STRING) {
        PredmainScreen(
            navController = navController,
            resContents = resContents
        )
    }

    val orientation = LocalConfiguration.current.orientation

    val imageUrl = when(orientation){
        Configuration.ORIENTATION_PORTRAIT -> PB2
        else -> LB2
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primarySurface,
    ) { paddingValues ->
        GlideImage(
            imageModel = imageUrl,
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            // shows a placeholder while loading the image.
//            placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
            // shows an error ImageBitmap when the request failed.
//            error = ImageBitmap.imageResource(R.drawable.error)
        )
    }

}

@Composable
fun PredmainScreen(
    navController: NavController,
    resContents: ResContents,
) {

    Log.e("qwer","PredmainScreen")

    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    LaunchedEffect(key1 = "key") {
        CoroutineScope(Dispatchers.Main).launch {
            resContents.resContents.let {
                if (it.length > 2) {
                    delay(1000)

//                    dopik(context, zovServaka.zovServaka)
//                    activity.finish()

                    navController.navigate(Route.MAIN)

                } else {
                    delay(1000)
                    navController.navigate(Route.MAIN)
                }
            }
        }
    }
}

