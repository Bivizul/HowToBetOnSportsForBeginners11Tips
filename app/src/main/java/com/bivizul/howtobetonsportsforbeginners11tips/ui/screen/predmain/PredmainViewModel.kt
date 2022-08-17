package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.predmain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.howtobetonsportsforbeginners11tips.data.ResContentsRepository
import com.bivizul.howtobetonsportsforbeginners11tips.data.model.SetRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PredmainViewModel @Inject constructor(
    private val resContentsRepository: ResContentsRepository,
) : ViewModel() {

    val resContents = resContentsRepository.resContents

    fun getResContents(setRes: SetRes) {
        viewModelScope.launch(Dispatchers.Main) {
            resContentsRepository.getResContents(setRes)
        }
    }
}
