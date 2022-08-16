package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.predmain

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bivizul.howtobetonsportsforbeginners11tips.data.ResContentsRepository
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.ResContents
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.SetRes
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PredmainViewModel @Inject constructor (
//    private val setRes: SetRes,
    private val resContentsRepository: ResContentsRepository,
) : ViewModel() {

    val resContents = resContentsRepository.resContents

    fun getResContents(setRes: SetRes) {
        viewModelScope.launch(Dispatchers.IO) {
            resContentsRepository.getResContents(setRes)
        }
    }



//    val a by resContentsRepository.getResContents(setRes).collectAsState(initial = SetRes(""))

//    fun a (setRes: SetRes){
//        viewModelScope.launch{
//            val a = getResContents(setRes)
//            val b = resContentsRepository.getResContents(setRes)
//            b.collectAsState(initial = )
//        }
//    }

}
