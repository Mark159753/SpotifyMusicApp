package com.example.login.ui

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.login.R
import com.example.ui.result_contracts.GetSpotifyAuthCode
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavHome:()->Unit
){

    val context = LocalContext.current

    LaunchedEffect(Unit){
        viewModel.action.collectLatest { action ->
            when(action){
                is LoginAction.Error -> {
                    Toast.makeText(
                        context, action.msg, Toast.LENGTH_LONG
                    ).show()
                }
                LoginAction.NavToHome -> onNavHome()
            }
        }
    }

    LoginScreen(
        onLogin = viewModel::onLogin
    )
}

@Composable
private fun LoginScreen(
    onLogin:(String)->Unit = {}
){
    val backgroundColor = MaterialTheme.colorScheme.background

    val authResult = rememberLauncherForActivityResult(
        contract = GetSpotifyAuthCode(),
        onResult = { code ->
            Log.i("CODE", code.toString())
            if (!code.isNullOrBlank()){
                onLogin(code)
            }
        }
    )

    BackgroundImage(
        painter = painterResource(id = R.drawable.artists),
        backgroundColor = backgroundColor
    ){
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.31f))

            Icon(
                painter = painterResource(id = R.drawable.spotify_icon),
                contentDescription = "Icon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(74.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.head_title_top),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = stringResource(id = R.string.head_title_bottom),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {
                          authResult.launch(null)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = stringResource(id = R.string.log_in_btn),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
private fun BackgroundImage(
    modifier: Modifier = Modifier,
    backgroundColor:Color = Color.Black,
    painter:Painter,
    content: @Composable BoxScope.()->Unit,
){
    Box(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxSize()
    ) {
        Image(
            painter = painter,
            contentDescription = "artists",
            contentScale = ContentScale.None,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .drawWithCache {
                    val brush = Brush.verticalGradient(
                        listOf(backgroundColor.copy(alpha = 0.1f), backgroundColor)
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush)
                    }
                }
        )

        content()
    }
}


@Preview
@Composable
private fun LoginScreenPreview(){
    Surface {
        LoginScreen()
    }
}