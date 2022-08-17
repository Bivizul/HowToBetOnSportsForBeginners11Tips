package com.bivizul.howtobetonsportsforbeginners11tips.data

import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ContentsObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ContentsRepository @Inject constructor(private val netService: NetService) {

    val contents = flow {
        emit(fromResponseToContents(netService.getContents()))
    }.flowOn(Dispatchers.IO)

    private fun fromResponseToContents(response: Response<ContentsObj>): ContentsObj? {
        var contents: ContentsObj? = null
        if (response.isSuccessful) {
            response.body()?.let {
                contents = it
            }
        }
        return contents
    }

}