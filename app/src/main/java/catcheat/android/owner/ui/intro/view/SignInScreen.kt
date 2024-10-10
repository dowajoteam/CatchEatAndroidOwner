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
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import catcheat.android.owner.ui.common.CustomButton
import catcheat.android.owner.ui.common.CustomButton2
import catcheat.android.owner.ui.common.CustomTextField
import catcheat.android.owner.ui.common.IntroScreens
import catcheat.android.owner.ui.common.Leave
import catcheat.android.owner.ui.common.MainScreens
import catcheat.android.owner.ui.intro.viewmodel.SignInViewModel
import catcheat.android.owner.ui.theme.Bold20TextStyle
import catcheat.android.owner.ui.theme.Bold30TextStyle
import catcheat.android.owner.ui.theme.CatchEat
import catcheat.android.owner.ui.theme.Regular15TextStyle

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavHostController
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val signInResult by viewModel.signInResult.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val isFormValid = id.isNotBlank() && password.isNotBlank() // 입력 값이 모두 채워졌는지 확인

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Leave(navController = navController, "로그인", false)

        Row {
            Text(text = "캐칫 사장님", style = Bold30TextStyle, color = CatchEat)
            Text(text = " 안녕하세요!", style = Bold30TextStyle, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // 아이디 입력
        CustomTextField(
            value = id,
            onValueChange = { id = it },
            placeholder = "아이디"
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 비밀번호 입력
        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "비밀번호",
            isPassword = true
        )

        // 로그인 결과 처리
        signInResult?.let { result ->
            if (result.token != null) {
                // 로그인 성공 시 홈 화면으로 이동
                navController.navigate(MainScreens.Home.name) {
                    popUpTo(IntroScreens.SignIn.name) { inclusive = true }
                }
            }
        }

        // 에러 메시지가 있을 때 화면에 출력
        errorMessage?.let { message ->
            val displayMessage = when (message) {
                "User is not approved." -> "아직 입점 승인 절차 중에 있습니다."
                "Invalid email or password" -> "아이디 혹은 비밀번호를 확인해주세요."
                else -> message // 기타 에러 메시지 처리
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = displayMessage,
                color = Color.Red,
                style = Regular15TextStyle
            )
        }

        // 로그인 버튼 클릭 시 로그인 시도
        CustomButton2(
            text = "로그인",
            onClick = {
                if (isFormValid) {
                    viewModel.signIn(id, password)
                }
            },
            enabled = isFormValid // 로그인 버튼이 조건에 따라 활성화됨
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "앗! 아직 캐칫에 등록되지 않은\n가게 사장님이신가요?",
            style = Bold20TextStyle, color = Color.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        CustomButton(text = "입점신청", onClick = {
            navController.navigate(IntroScreens.TOS.name)
        })
    }
}

@Preview()
@Composable
fun SignInScreenPreview() {
    SignInScreen(navController = NavHostController(LocalContext.current))
}