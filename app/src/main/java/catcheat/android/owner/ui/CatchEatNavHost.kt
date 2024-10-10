package catcheat.android.owner.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import catcheat.android.owner.ui.accessibility.view.AccessibilityUploadScreen
import catcheat.android.owner.ui.accessibility.view.PhysicalOtherItems
import catcheat.android.owner.ui.accessibility.view.PhysicalRequiredItems
import catcheat.android.owner.ui.accessibility.view.ServiceOtherItems
import catcheat.android.owner.ui.accessibility.view.ServiceRequiredItems
import catcheat.android.owner.ui.accessibility.view.VisualOtherItems
import catcheat.android.owner.ui.accessibility.view.VisualRequiredItems
import catcheat.android.owner.ui.common.AccessibilityScreens
import catcheat.android.owner.ui.common.IntroScreens
import catcheat.android.owner.ui.common.MainScreens
import catcheat.android.owner.ui.common.RankingDetailsScreens
import catcheat.android.owner.ui.intro.view.OnBoarding1Screen
import catcheat.android.owner.ui.intro.view.OnBoarding2Screen
import catcheat.android.owner.ui.intro.view.SignInScreen
import catcheat.android.owner.ui.intro.view.SignUpCompletedScreen
import catcheat.android.owner.ui.intro.view.SignUpScreen
import catcheat.android.owner.ui.intro.view.TOSScreen
import catcheat.android.owner.ui.main.view.HomeScreen
import catcheat.android.owner.ui.main.view.MainNav
import catcheat.android.owner.ui.main.view.RankingScreen
import catcheat.android.owner.ui.ranking.view.RankingDetailsScreen

@Composable
fun CatchEatNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = IntroScreens.SignIn.name
    ) {
        // IntroScreens
        composable(IntroScreens.OnBoarding1.name) {
            OnBoarding1Screen()
        }
        composable(IntroScreens.OnBoarding2.name) {
            OnBoarding2Screen()
        }
        composable(IntroScreens.SignIn.name) {
            SignInScreen(navController = navController)
        }
        composable(IntroScreens.TOS.name) {
            TOSScreen(navController = navController)
        }
        composable(IntroScreens.SignUp.name) {
            SignUpScreen(navController = navController)
        }
        composable(IntroScreens.SignUpCompleted.name) {
            SignUpCompletedScreen()
        }

        // MainScreens
        composable(MainScreens.Home.name) {
            MainScreensLayout(
                currentScreen = MainScreens.Home,
                navController = navController
            )
        }
        composable(MainScreens.Ranking.name) {
            MainScreensLayout(
                currentScreen = MainScreens.Ranking,
                navController = navController
            )
        }

        // AccessibilityScreens
        composable(
            route = "${AccessibilityScreens.AccessibilityUpload.name}/{type}",
            arguments = listOf(navArgument("type") { type = NavType.IntType })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getInt("type") ?: 0
            AccessibilityUploadScreen(navController = navController, type = type)
        }
        composable(AccessibilityScreens.PhysicalRequired.name) {
            PhysicalRequiredItems()
        }
        composable(AccessibilityScreens.PhysicalOther.name) {
            PhysicalOtherItems()
        }
        composable(AccessibilityScreens.ServiceRequired.name) {
            ServiceRequiredItems()
        }
        composable(AccessibilityScreens.ServiceOther.name) {
            ServiceOtherItems()
        }
        composable(AccessibilityScreens.VisualRequired.name) {
            VisualRequiredItems()
        }
        composable(AccessibilityScreens.VisualOther.name) {
            VisualOtherItems()
        }

        // RankingDetailsScreens
        composable(
            route = "${RankingDetailsScreens.RankingDetails.name}/{storeId}",
            arguments = listOf(navArgument("storeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val storeId = backStackEntry.arguments?.getInt("storeId") ?: 0
            RankingDetailsScreen(navController = navController, storeId = storeId)
        }


    }
}

@Composable
fun MainScreensLayout(currentScreen: MainScreens, navController: NavHostController) {
    var selectedScreen by remember { mutableStateOf(currentScreen) }

    Column(modifier = Modifier.fillMaxSize()) {
        // MainNav는 MainScreens에서만 사용
        MainNav(currentScreen = selectedScreen, onTabSelected = {
            selectedScreen = it
            navController.navigate(it.name)
        })

        Box(modifier = Modifier.weight(1f)) {
            when (selectedScreen) {
                MainScreens.Home -> HomeScreen(navController = navController)
                MainScreens.Ranking -> RankingScreen(navController = navController)
            }
        }
    }
}





