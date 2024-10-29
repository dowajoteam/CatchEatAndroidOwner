package catcheat.android.owner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import catcheat.android.owner.ui.CatchEatNavHost
import catcheat.android.owner.ui.intro.view.OnBoarding1Screen
import catcheat.android.owner.ui.intro.view.OnBoarding2Screen
import catcheat.android.owner.ui.theme.CatchEatAndroidOwnerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatchEatAndroidOwnerTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var screenIndex by remember { mutableStateOf(0) }
    var showNavHost by remember { mutableStateOf(false) }

    // 2초마다 화면을 전환하는 타이머
    LaunchedEffect(screenIndex) {
        if (screenIndex < 2) {
            delay(2000) // 2초 딜레이
            screenIndex++
        } else {
            // 모든 온보딩 화면이 끝나면 네비게이션 호스트를 표시
            showNavHost = true
        }
    }

    if (showNavHost) {
        // 네비게이션 호스트로 이동 (온보딩이 끝난 후)
        val navController = rememberNavController()
        CatchEatNavHost(navController = navController)
    } else {
        // 온보딩 화면 순차적으로 표시
        when (screenIndex) {
            0 -> OnBoarding1Screen()  // 첫 번째 온보딩 화면
            1 -> OnBoarding2Screen()  // 두 번째 온보딩 화면
        }
    }
}