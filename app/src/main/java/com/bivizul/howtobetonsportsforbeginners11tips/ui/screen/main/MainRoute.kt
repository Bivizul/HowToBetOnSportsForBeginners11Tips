package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.bivizul.howtobetonsportsforbeginners11tips.R
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.Contents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ContentsObj
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.Tip
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainRoute(
    viewModel: ContentsViewModel,
) {

    Log.e("qwer", "MainRoute")

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

    Log.e("qwer", "MainScreen")

    val pagerState = rememberPagerState(initialPage = 0)

    val toolbarHeight = 96.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }

//    Log.e("qwer","toolbarHeight : $toolbarHeight")
//    Log.e("qwer","toolbarHeightPx : $toolbarHeightPx")
    Log.e("qwer", "toolbarOffsetHeightPx : ${toolbarOffsetHeightPx.value.roundToInt()}")
//    val a = toolbarOffsetHeightPx.value

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
        TabsContent(
            pagerState = pagerState,
            contents = contents,
            toolbarOffsetHeightPx = toolbarOffsetHeightPx
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
                    style = TextStyle(color = Color.White),
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


@ExperimentalPagerApi
@Composable
fun Tabs(
    pagerState: PagerState,
    contents: Contents,
) {
    val list = contents.tips
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSurface,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
//                icon = {
//                    Icon(imageVector = list[index].second, contentDescription = null)
//                },
                text = {
                    Text(
                        list[index].id.toString(),
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    contents: Contents,
    toolbarOffsetHeightPx: MutableState<Float>,
) {
    HorizontalPager(count = contents.tips.size, state = pagerState) { page ->
        TabContentScreen(
            tip = contents.tips[page],
            toolbarOffsetHeightPx = toolbarOffsetHeightPx
        )
    }
}

@Composable
fun TabContentScreen(
    tip: Tip,
    state: ScrollState = rememberScrollState(),
    toolbarOffsetHeightPx: MutableState<Float>,
) {

//    Box(
//        modifier = Modifier
////            .fillMaxSize()
////            .padding(top=100.dp)
//            .fillMaxWidth()
//            .requiredHeight(toolbarHeight)
//    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .graphicsLayer { translationY = (toolbarOffsetHeightPx.value) }
//            .padding(top = 96.dp)
//                .fillMaxWidth()
//                .requiredHeight(toolbarHeight)
            .verticalScroll(state),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = tip.nameTips,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = tip.descriptionTips,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
//    }

}
