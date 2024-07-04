package com.abi.livestreamingtest.screens.streamingScreen

import androidx.annotation.RawRes
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.abi.livestreamingtest.R
import com.abi.livestreamingtest.ui.theme.DarkGreyColor
import com.abi.livestreamingtest.ui.theme.LiveColor
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun CurrentProfileLiveAndControlsView(liveIconSizeAnimation : Dp,
                                      isMicrophoneON : Boolean,
                                      onMicroPhoneClick: () -> Unit,
                                      onCameraSwitchClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.height(height = 24.dp))

            Image(painter = painterResource(id = R.drawable.user_image),
                modifier = Modifier
                    .size(38.dp)
                    .clip(shape = CircleShape),
                contentDescription = null)

            Spacer(modifier = Modifier.width(width = 8.dp))

            Text(text = "Abijith",
                modifier = Modifier
                    .weight(weight = 1F)
                    .fillMaxWidth(),
                fontWeight = W600,
                color = Color.White,
                fontSize = 16.sp)

            Spacer(modifier = Modifier.width(width = 8.dp))


            Row(modifier = Modifier
                .background(color = LiveColor, shape = RoundedCornerShape(size = 8.dp))
                .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(color = Color.White)
                    .size(size = liveIconSizeAnimation))

                Spacer(modifier = Modifier.width(width = 4.dp))
                Text(text = "Live",
                    color = Color.White,
                    fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.width(width = 8.dp))

            Text(text = "4.2k",
                modifier = Modifier,
                fontWeight = W600,
                color = Color.White,
                fontSize = 13.sp)
        }

        VideoControlsView(modifier = Modifier.align(alignment = Alignment.End),

            onMicroPhoneClick = onMicroPhoneClick,
            isMicrophoneON = isMicrophoneON,
            onCameraSwitchClick = onCameraSwitchClick
        )
    }


}

@Composable
fun CameraAndroidView(cameraController : LifecycleCameraController) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            PreviewView(context).apply {
                scaleType = PreviewView.ScaleType.FILL_START
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                controller = cameraController
            }
        },
        onRelease = {
            cameraController.unbind()
        }
    )
}

@Composable
fun CommentView(modifier : Modifier,
                onLiveEndClick : () -> Unit) {

    var text by remember { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(all = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(value = text,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = DarkGreyColor,
                unfocusedContainerColor = DarkGreyColor,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            ),
            maxLines = 1,
            shape = RoundedCornerShape(size = 16.dp),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Color.White
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            placeholder = {
                Text(text = "Say Something!!!",
                    fontSize = 14.sp,
                    color = Color.White
                )
            },
            trailingIcon = {
                IconButton(onClick = { focusManager.clearFocus() }) {
                    Icon(painter = painterResource(id = R.drawable.ic_send),
                        tint = Color.Unspecified,
                        contentDescription = null)
                }
            },
            onValueChange = { text = it })

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .weight(weight = 1F))

        IconButton(onClick = onLiveEndClick,
            modifier = Modifier.background(color = DarkGreyColor, shape = CircleShape)
        ) {
            Icon(imageVector = Icons.Default.Close,
                contentDescription = null)
        }
    }
}

@Composable
fun VideoControlsView(modifier : Modifier,
                      isMicrophoneON : Boolean,
                      onMicroPhoneClick : () -> Unit,
                      onCameraSwitchClick : () -> Unit) {

    Column(verticalArrangement = Arrangement.Center,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onMicroPhoneClick) {
            Icon(painter = painterResource(id = if (isMicrophoneON) R.drawable.ic_microphone else R.drawable.no_mircrophone),
                tint = Color.White,
                contentDescription = null)
        }

        IconButton(onClick = onCameraSwitchClick) {
            Icon(painter = painterResource(id = R.drawable.ic_switch_camera),
                modifier = Modifier.size(size = 20.dp),
                tint = Color.Unspecified,
                contentDescription = null)
        }
    }
}

@Composable
fun LottieAnimationView(modifier: Modifier = Modifier,
                        onCompletion : () -> Unit,
                        @RawRes file : Int) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(file))

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1)

    if (progress == 1F) {
        onCompletion()
    }

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )

}