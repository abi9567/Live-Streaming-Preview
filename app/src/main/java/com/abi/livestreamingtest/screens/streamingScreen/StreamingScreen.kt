

import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.abi.livestreamingtest.R
import com.abi.livestreamingtest.screens.streamingScreen.CameraAndroidView
import com.abi.livestreamingtest.screens.streamingScreen.CommentView
import com.abi.livestreamingtest.screens.streamingScreen.CurrentProfileLiveAndControlsView
import com.abi.livestreamingtest.screens.streamingScreen.LottieAnimationView
import com.abi.livestreamingtest.screens.streamingScreen.StreamingViewModel

@Composable
fun StreamingScreen(
    navController : NavController,
    viewModel : StreamingViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val isMicrophoneON by viewModel.isMicroPhoneON.collectAsState(initial = false)
    val isFrontCamera by viewModel.isFrontCameraVisible.collectAsState(initial = false)
    val isLottieAnimationVisible by viewModel.isLottieAnimationVisible.collectAsState(initial = false)

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            bindToLifecycle(lifecycleOwner)
        }
    }

    LaunchedEffect(key1 = isFrontCamera) {
        cameraController.cameraSelector = if (isFrontCamera) CameraSelector.DEFAULT_FRONT_CAMERA
        else CameraSelector.DEFAULT_BACK_CAMERA
    }

    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Transition")
    val liveIconSizeAnimation by infiniteTransition.animateValue(
        label = "Icon Size Animation",
        initialValue = 8.dp,
        targetValue = 11.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Reverse,
            animation = tween(durationMillis = 900)
        )
    )

    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {

            CameraAndroidView(cameraController = cameraController)

            if (isLottieAnimationVisible) {
                LottieAnimationView(file = R.raw.live_animation,
                    modifier = Modifier.align(alignment = Alignment.Center),
                    onCompletion = viewModel::hideLottieAnimationVisibility
                )
            }

            CurrentProfileLiveAndControlsView(liveIconSizeAnimation = liveIconSizeAnimation,
                isMicrophoneON = isMicrophoneON,
                onCameraSwitchClick = viewModel::toggleCameraView,
                onMicroPhoneClick = viewModel::setMicroPhone
            )

            CommentView(modifier = Modifier.align(alignment = Alignment.BottomStart),
                onLiveEndClick = { navController.navigateUp() }
            )
        }
    }
}

