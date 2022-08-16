package com.bivizul.howtobetonsportsforbeginners11tips.data.model

import androidx.annotation.Keep

@Keep
@kotlinx.serialization.Serializable
data class Tip(
    val descriptionTips: String,
    val id: Int,
    val nameTips: String
)