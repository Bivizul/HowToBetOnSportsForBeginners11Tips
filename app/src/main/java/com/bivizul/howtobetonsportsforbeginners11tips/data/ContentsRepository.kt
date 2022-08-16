package com.bivizul.howtobetonsportsforbeginners11tips.data

import android.util.Log
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.EMPTY_STRING
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.Contents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ContentsObj
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ResContents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.SetRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

class ContentsRepository @Inject constructor(private val netService: NetService) {


//    private val _contents = MutableSharedFlow<Contents>()
//    val contents : SharedFlow<Contents> = _contents.asSharedFlow()
//
//    suspend fun getContents(){
//        val result = fromResponseToContents(netService.getContents())
//        _contents.emit(result)
//    }

    val contents = flow {
        emit(fromResponseToContents(netService.getContents()))
    }.flowOn(Dispatchers.IO)

    private fun fromResponseToContents(response: Response<ContentsObj>): ContentsObj? {
        Log.e("qwer","response : $response")
//        var contents : Contents = Contents(EMPTY_STRING, emptyList())
        var contents : ContentsObj? = null
        if (response.isSuccessful) {
            response.body()?.let {
                contents = it
            }
        }
        return contents
    }

}