package com.bivizul.howtobetonsportsforbeginners11tips.data

import android.util.Log
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.ERROR_STRING
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ResContents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.SetRes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.Response
import javax.inject.Inject

class ResContentsRepository @Inject constructor(
    private val netService: NetService,
) {

    private val _resContents = MutableSharedFlow<ResContents>()
    val resContents: SharedFlow<ResContents> = _resContents.asSharedFlow()

    suspend fun getResContents(setRes: SetRes) {
        val result = fromResponseToResContents(netService.getResContents(setRes))
        _resContents.emit(result)
    }

    private fun fromResponseToResContents(response: Response<ResContents>): ResContents {
        var resContents = ResContents(ERROR_STRING)
        if (response.isSuccessful) {
            response.body()?.let {
                resContents = it
            }
        }
        Log.e("qwer", "ResContentsRepository resContents: $resContents")
        return resContents
    }

}