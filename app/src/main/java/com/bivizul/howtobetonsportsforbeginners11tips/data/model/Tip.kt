package com.bivizul.howtobetonsportsforbeginners11tips.data.model

import androidx.annotation.Keep

@Keep
data class Tip(
    val descriptionTips: String,
    val id: Int,
    val nameTips: String,
)