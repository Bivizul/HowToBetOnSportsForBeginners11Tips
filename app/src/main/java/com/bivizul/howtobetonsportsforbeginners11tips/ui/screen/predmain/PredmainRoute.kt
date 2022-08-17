package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.predmain

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.EMPTY_STRING
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.ERROR_STRING
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.KEY
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.LB2
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.PB2
import com.bivizul.howtobetonsportsforbeginners11tips.data.getError
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ResContents
import com.bivizul.howtobetonsportsforbeginners11tips.ui.PredMainActivity
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
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val resContents by viewModel.resContents.collectAsState(initial = ResContents(EMPTY_STRING))
    val orientation = LocalConfiguration.current.orientation
    val imageUrl = when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> PB2
        else -> LB2
    }

    if (resContents.resContents != EMPTY_STRING && resContents.resContents != ERROR_STRING) {
        PredmainScreen(
            navController = navController,
            resContents = resContents
        )
    } else if (resContents.resContents == ERROR_STRING) {
        getError(context, activity)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primarySurface,
    ) { paddingValues ->
        GlideImage(
            imageModel = imageUrl,
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun PredmainScreen(
    navController: NavController,
    resContents: ResContents,
) {

    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    LaunchedEffect(key1 = "key") {
        CoroutineScope(Dispatchers.Main).launch {
            resContents.resContents.let {
                if (it.length > 2) {
                    delay(1000)
                    val intent = Intent(activity, PredMainActivity::class.java)
                    intent.putExtra(KEY, it)
                    startActivity(context, intent, bundleOf())
                } else {
                    delay(1000)
                    navController.navigate(Route.MAIN)
                }
            }
        }
    }
}

