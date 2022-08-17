package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.Contents
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

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
        contentColor = MaterialTheme.colors.onPrimary,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = MaterialTheme.colors.onPrimary
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        list[index].id.toString(),
                        color = if (pagerState.currentPage == index) {
                            MaterialTheme.colors.onPrimary
                        } else {
                            MaterialTheme.colors.onBackground
                        }
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