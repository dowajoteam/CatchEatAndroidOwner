package catcheat.android.owner.ui.intro.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import catcheat.android.owner.ui.common.CustomButton
import catcheat.android.owner.ui.common.CustomButton2
import catcheat.android.owner.ui.common.IntroScreens
import catcheat.android.owner.ui.common.Leave
import catcheat.android.owner.ui.common.TOSCheck
import catcheat.android.owner.ui.theme.Bold30TextStyle
import catcheat.android.owner.ui.theme.CatchEat

@Composable
fun TOSScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Leave(navController = navController, "서비스 이용 약관", false)

        Column(horizontalAlignment = Alignment.Start) {
            Row {
                Text("캐칫 사장님", style = Bold30TextStyle, color = CatchEat)
                Text("서비스 이용", style = Bold30TextStyle, color = Color.Black)
            }
            Text("약관에 동의해주세요.", style = Bold30TextStyle, color = Color.Black)

            var status1 by remember { mutableStateOf(false) } // 초기 상태는 false
            var status2 by remember { mutableStateOf(false) } // 초기 상태는 false
            val isFormValid = status1 && status2 // 모두 true임을 확인
            Spacer(modifier = Modifier.height(30.dp))
            TOSCheck("필수", "캐칫 회원 약관 및 동의사항", status1) {newStatus -> status1 = newStatus}
            Spacer(modifier = Modifier.height(5.dp))
            TOSCheck("필수", "본인 확인 서비스 약관 및 동의사항", status2) {newStatus -> status2 = newStatus}

            Spacer(modifier = Modifier.height(400.dp))
            CustomButton2(
                "동의하기",
                onClick = {
                    if (isFormValid) {
                        navController.navigate(IntroScreens.SignUp.name)
                    }
                },
                enabled = isFormValid
            )
        }
    }
}

@Preview()
@Composable
fun TOSScreenPreview() {
    TOSScreen(navController = NavHostController(LocalContext.current))
}