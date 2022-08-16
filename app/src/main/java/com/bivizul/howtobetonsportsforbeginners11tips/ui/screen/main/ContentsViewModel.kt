package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.howtobetonsportsforbeginners11tips.data.ContentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentsViewModel @Inject constructor(private val contentsRepository: ContentsRepository):ViewModel() {

    val contents = contentsRepository.contents

//    init {
//        getContents()
//    }
//
//    fun getContents(){
//        viewModelScope.launch(Dispatchers.IO) {
//            contentsRepository.getContents()
//        }
//    }

}