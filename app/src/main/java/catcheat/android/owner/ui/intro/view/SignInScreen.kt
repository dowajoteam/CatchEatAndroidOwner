package catcheat.android.owner.ui.intro.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import catcheat.android.owner.ui.common.CustomButton
import catcheat.android.owner.ui.common.CustomTextField
import catcheat.android.owner.ui.common.IntroScreens
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

    // 아이디 및 비밀번호 유효성 검사 통과 여부
    val isIdValid = remember(id) { id.isNotBlank() && viewModel.isIdValid(id) }
    val isPasswordValid = remember(password) { password.isNotBlank() && viewModel.isPasswordValid(password) }
    // 전체 입력 값 유효성 확인
    val isFormValid = isIdValid && isPasswordValid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))

            Row (
                modifier = Modifier.semantics { contentDescription = "캐칫 사장님 안녕하세요!" }
            ) {
                Text(text = "캐칫 사장님", style = Bold30TextStyle, color = CatchEat, modifier = Modifier.clearAndSetSemantics {})
                Text(text = " 안녕하세요!", style = Bold30TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
            }

            Spacer(modifier = Modifier.height(30.dp))

            // 아이디 입력
            CustomTextField(
                value = id,
                onValueChange = { id = it },
                placeholder = "아이디"
            )
            // 유효성 검사에 따라 오류 메시지 표시
            if (!isIdValid && id.isNotBlank()) {
                Text(
                    text = "5~10자, 영문 소문자/숫자를 포함해야 합니다.",
                    color = Color.Red,
                    style = Regular15TextStyle,
                    modifier = Modifier
                        .width(350.dp)
                        .height(15.dp)
                )
            } else {
                Spacer(modifier = Modifier.height(15.dp))
            }

            Spacer(modifier = Modifier.height(15.dp))

            // 비밀번호 입력
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "비밀번호",
                isPassword = true
            )
            if (!isPasswordValid && password.isNotBlank()) {
                Text(
                    text = "8~16자, 영문 소문자/숫자/특수문자를 포함해야 합니다.",
                    color = Color.Red,
                    style = Regular15TextStyle,
                    modifier = Modifier
                        .width(350.dp)
                        .height(15.dp)
                )
            } else {
                Spacer(modifier = Modifier.height(15.dp))
            }

            // 로그인 결과 처리
            signInResult?.let { result ->
                LaunchedEffect(result.token) {
                    if (result.token != null) {
                        // 로그인 성공 시 홈 화면으로 이동
                        navController.navigate(MainScreens.Home.name) {
                            popUpTo(IntroScreens.SignIn.name) { inclusive = true }
                        }
                    }
                }
            }
            if (errorMessage != null) {
                val displayMessage = when (errorMessage) {
                    "User is not approved." -> "아직 입점 승인 절차 중에 있습니다."
                    "Invalid email or password" -> "아이디 혹은 비밀번호를 확인해주세요."
                    else -> errorMessage ?: ""
                }
                Box(
                    modifier = Modifier
                        .width(350.dp)
                        .height(30.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = displayMessage,
                        color = Color.Red,
                        style = Regular15TextStyle,
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(30.dp))
            }

            // 로그인 버튼 클릭 시 로그인 시도
            CustomButton(
                text = "로그인",
                onClick = {
                    if (isFormValid) {
                        viewModel.signIn(id, password)
                    }
                },
                enabled = isFormValid
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "앗! 아직 캐칫에 등록되지 않은\n가게 사장님이신가요?",
                style = Bold20TextStyle,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomButton(text = "입점신청", onClick = {
                navController.navigate(IntroScreens.TOS.name)
            })
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}