package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.bivizul.howtobetonsportsforbeginners11tips.R
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.Contents
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainRoute(
    viewModel: MainViewModel,
) {
    val contents by viewModel.contents.collectAsState(initial = null)

    if (contents != null) {
        contents?.let { MainScreen(contents = it.contents) }
    }
}

@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
fun MainScreen(
    contents: Contents,
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val orientation = LocalConfiguration.current.orientation
    val imageUrl = when (orientation) {
        Configuration.ORIENTATION_PORTRAIT -> Constant.PB2
        else -> Constant.LB2
    }
    val toolbarHeight = 96.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {

        GlideImage(
            imageModel = imageUrl,
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        )
        TabsContent(
            pagerState = pagerState,
            contents = contents,
            toolbarOffsetHeightPx = toolbarOffsetHeightPx.value
        )

        TopAppBar(
            modifier = Modifier
                .height(toolbarHeight)
                .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = TextStyle(color = MaterialTheme.colors.onPrimary),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(
                        18F,
                        TextUnitType.Sp
                    ),
                    modifier = Modifier.padding(all = Dp(5F)),
                    textAlign = TextAlign.Center
                )
                Tabs(
                    pagerState = pagerState,
                    contents = contents
                )
            }
        }
    }
}