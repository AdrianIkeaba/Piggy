package com.ghostdev.piggy.presentation.view.ui.onboarding

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.ghostdev.piggy.R
import com.ghostdev.piggy.data.navigation.NavDestinations
import com.ghostdev.piggy.presentation.theme.KronaOne
import com.ghostdev.piggy.presentation.theme.primary
import com.ghostdev.piggy.presentation.theme.tertiary
import com.ghostdev.piggy.presentation.theme.tertiary2
import kotlinx.coroutines.delay

@Composable
fun OnboardingScreen(controller: NavController) {
    val slideIn = remember {
        Animatable(1000f)
    }

    LaunchedEffect(Unit) {
        delay(1000)
        slideIn.animateTo(
            targetValue = 0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    val imageLoader = ImageLoader.Builder(LocalContext.current.applicationContext)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    val painter = rememberImagePainter(data = R.drawable.piggy,
        imageLoader = imageLoader,
        builder = {
            placeholder(null)
            error(null)
        })

    Column(modifier = Modifier
        .fillMaxSize()
        .background(primary)) {
        Spacer(modifier = Modifier.fillMaxHeight(0.25f))
        Image(painter = painter, contentDescription = "Piggy logo",
            modifier = Modifier
                .requiredSize(width = 250.dp, height = 250.dp)
                .align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Monitor your savings with your personal piggy bank.",
            fontSize = 14.sp, color = Color.Black, fontFamily = KronaOne, textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 20.dp, end = 20.dp))

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)) {
            Button(onClick = {
                controller.navigate(NavDestinations.Home.toString())
            },
                modifier = Modifier
                    .offset(x = slideIn.value.dp)
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(containerColor = tertiary2),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(3.dp, tertiary)
            ) {
                Text(text = "Get Started", fontFamily = KronaOne, color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
            }
        }
    }
}
