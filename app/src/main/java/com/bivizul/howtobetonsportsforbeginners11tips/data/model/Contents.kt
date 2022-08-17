package com.bivizul.howtobetonsportsforbeginners11tips.data.model

import androidx.annotation.Keep

@Keep
data class Contents(
    val intro: String,
    val tips: List<Tip>,
)