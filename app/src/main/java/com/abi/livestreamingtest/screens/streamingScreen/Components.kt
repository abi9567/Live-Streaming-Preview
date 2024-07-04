package com.abi.livestreamingtest.screens.streamingScreen

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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

@Composable
fun CurrentProfileLiveView() {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Transition")
    val liveIconSizeAnimation by infiniteTransition.animateValue(
        label = "Icon Size Animation",
        initialValue = 8.dp,
        targetValue = 11.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Reverse,
            animation = tween(durationMillis = 900)))

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
}

@Composable
fun CameraAndroidView(cameraController : LifecycleCameraController) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            PreviewView(ctx).apply {
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
fun CommentView(modifier : Modifier) {

    var text by remember { mutableStateOf(value = "") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(value = text,
        modifier = modifier.padding(all = 20.dp),
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
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_send),
                    tint = Color.Unspecified,
                    contentDescription = null)
            }
        },
        onValueChange = { text = it })
}