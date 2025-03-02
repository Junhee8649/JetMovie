package com.github.junhee8649.jetmovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.github.junhee8649.jetmovie.ui.home.HomeScreen
import com.github.junhee8649.jetmovie.ui.theme.JetMovieTheme
import dagger.hilt.android.AndroidEntryPoint

// Dagger Hilt에게 '이것은 실제로 Android 컴포넌트입니다'라고 알리는 역할
// 의존성을 사용할 진입점을 알림
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetMovieTheme {
                HomeScreen {  }
            }
        }
    }
}