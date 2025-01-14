package com.abi.livestreamingtest.screens.streamingScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class StreamingViewModel : ViewModel() {

    private val _isMicroPhoneON = MutableStateFlow(value = true)
    val isMicroPhoneON : Flow<Boolean> = _isMicroPhoneON

    private val _isFrontCameraVisible = MutableStateFlow(value = true)
    val isFrontCameraVisible : Flow<Boolean> = _isFrontCameraVisible

    private val _isLottieAnimationVisible = MutableStateFlow(value = true)
    val isLottieAnimationVisible : Flow<Boolean> = _isLottieAnimationVisible

    fun setMicroPhone() {
        _isMicroPhoneON.value = !_isMicroPhoneON.value
    }

    fun toggleCameraView() {
        _isFrontCameraVisible.value = !_isFrontCameraVisible.value
    }

    fun hideLottieAnimationVisibility() {
        _isLottieAnimationVisible.value = false
    }
}