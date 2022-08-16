package com.bivizul.howtobetonsportsforbeginners11tips.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
@kotlinx.serialization.Serializable
data class ResContents(
    @SerializedName("url")
    val resContents: String
)
