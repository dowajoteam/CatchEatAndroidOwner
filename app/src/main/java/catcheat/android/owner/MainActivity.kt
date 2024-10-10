package catcheat.android.owner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import catcheat.android.owner.ui.CatchEatNavHost
import catcheat.android.owner.ui.theme.CatchEatAndroidOwnerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatchEatAndroidOwnerTheme {

                // NavController 생성
                val navController = rememberNavController()
                // CatchEatNavHost에 navController 전달
                CatchEatNavHost(navController = navController)

            }
        }
    }
}