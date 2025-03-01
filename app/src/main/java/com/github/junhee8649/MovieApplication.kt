package com.github.junhee8649

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// 모듈이 생성되도록 시작하기 위해 컨테이너를 제공해야하는데
// Dagger Hilt가 이러한 컨테이너를 생성하기 위해서는 애플리케이션 클래스를 만들고 하나의 어노테이션을 선언해야 함
@HiltAndroidApp
class MovieApplication: Application() {
}