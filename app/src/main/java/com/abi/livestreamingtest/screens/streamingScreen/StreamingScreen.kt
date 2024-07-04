

import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavController
import com.abi.livestreamingtest.screens.streamingScreen.CameraAndroidView
import com.abi.livestreamingtest.screens.streamingScreen.CommentView
import com.abi.livestreamingtest.screens.streamingScreen.CurrentProfileLiveView

@Composable
fun StreamingScreen(
    navController : NavController
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraController = remember {
        LifecycleCameraController(context).apply {
            bindToLifecycle(lifecycleOwner)
        }
    }

    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            CameraAndroidView(cameraController = cameraController)
            CurrentProfileLiveView()
            CommentView(modifier = Modifier.align(alignment = Alignment.BottomStart))
        }
    }
}

