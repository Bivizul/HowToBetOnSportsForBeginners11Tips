package com.bivizul.howtobetonsportsforbeginners11tips.data

import com.bivizul.howtobetonsportsforbeginners11tips.data.model.Contents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ContentsObj
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ResContents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.SetRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetService {

    @GET("22HowToBetOnSportsForBeginners11Tips/contents.json")
    suspend fun getContents(): Response<ContentsObj>

    @POST("22HowToBetOnSportsForBeginners11Tips/rescontents.php")
    suspend fun getResContents(@Body setRes: SetRes): Response<ResContents>

}