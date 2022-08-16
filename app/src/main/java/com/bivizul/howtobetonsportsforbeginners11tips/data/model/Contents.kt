package com.bivizul.howtobetonsportsforbeginners11tips.data.model

import androidx.annotation.Keep

@Keep
@kotlinx.serialization.Serializable
data class Contents(
    val intro: String,
    val tips: List<Tip>
)