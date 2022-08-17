package com.bivizul.howtobetonsportsforbeginners11tips.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.bivizul.howtobetonsportsforbeginners11tips.data.checkNetwork
import com.bivizul.howtobetonsportsforbeginners11tips.data.getError
import com.bivizul.howtobetonsportsforbeginners11tips.ui.navigation.NavGraph
import com.bivizul.howtobetonsportsforbeginners11tips.ui.theme.HowToBetOnSportsForBeginners11TipsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HowToBetOnSportsForBeginners11TipsTheme {
                if (checkNetwork(this)) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        NavGraph()
                    }
                } else {
                    getError(this, this)
                }
            }
        }
    }
}
