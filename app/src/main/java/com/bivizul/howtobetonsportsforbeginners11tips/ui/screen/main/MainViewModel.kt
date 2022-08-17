package com.bivizul.howtobetonsportsforbeginners11tips.ui.screen.main

import androidx.lifecycle.ViewModel
import com.bivizul.howtobetonsportsforbeginners11tips.data.ContentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(contentsRepository: ContentsRepository) : ViewModel() {

    val contents = contentsRepository.contents

}