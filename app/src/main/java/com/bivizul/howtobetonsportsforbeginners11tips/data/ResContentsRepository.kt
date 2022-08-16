package com.bivizul.howtobetonsportsforbeginners11tips.data

import android.util.Log
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.ERROR_STRING
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ResContents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.SetRes
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

class ResContentsRepository @Inject constructor(
    private val netService: NetService,
){
//    suspend fun getResContents(setRes: SetRes) = netService.getResContents(setRes)

    private val _resContents = MutableSharedFlow<ResContents>()
    val resContents : SharedFlow<ResContents> = _resContents.asSharedFlow()

    suspend fun getResContents(setRes: SetRes){
        val result = fromResponseToResContents(netService.getResContents(setRes))
        _resContents.emit(result)
    }

//    fun getResContents(setRes: SetRes) = flow {
//        emit(fromResponseToResContents(netService.getResContents(setRes)))
//    }.flowOn(Dispatchers.IO)

    private fun fromResponseToResContents(response: Response<ResContents>): ResContents {
        var resContents = ResContents(ERROR_STRING)
//        var resContents : ResContents? = null
        if (response.isSuccessful) {
            response.body()?.let {
                Log.e("qwer","resContents body : $it")
                resContents = it
            }
        }
        Log.e("qwer","ResContentsRepository resContents: $resContents")
        return resContents
    }

}