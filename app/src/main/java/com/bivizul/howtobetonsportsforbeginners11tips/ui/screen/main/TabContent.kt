package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.Contents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.Tip
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    contents: Contents,
    toolbarOffsetHeightPx: Float,
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
    toolbarOffsetHeightPx: Float,
) {

    val padding = when {
        toolbarOffsetHeightPx > -150f -> 100.dp
        else -> 0.dp
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = padding)
            .verticalScroll(state),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = tip.nameTips,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = tip.descriptionTips,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Start
        )
    }
}