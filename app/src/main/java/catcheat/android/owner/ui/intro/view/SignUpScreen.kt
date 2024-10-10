package catcheat.android.owner.ui.intro.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import catcheat.android.owner.R
import catcheat.android.owner.ui.common.CustomButton
import catcheat.android.owner.ui.common.CustomTextField
import catcheat.android.owner.ui.common.Leave
import catcheat.android.owner.ui.intro.viewmodel.SignUpViewModel
import catcheat.android.owner.ui.theme.Bold30TextStyle
import catcheat.android.owner.ui.theme.CatchEat
import catcheat.android.owner.ui.theme.Regular15TextStyle
import catcheat.android.owner.ui.theme.Regular20TextStyle
import coil3.compose.rememberAsyncImagePainter

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var storeName by remember { mutableStateOf("") }
    var businessType by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val signUpResponse by viewModel.signUpResponse.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // 이미지 선택을 위한 launcher 설정
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        viewModel.setImageUri(uri)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Leave(navController = navController, "입점 신청", false)

        Row {
            Text(text = "캐칫 사장님", style = Bold30TextStyle, color = CatchEat)
            Text(text = " 입점 신청", style = Bold30TextStyle, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                // 아이디 입력
                CustomTextField(value = id, onValueChange = { id = it }, placeholder = "아이디")
                Spacer(modifier = Modifier.height(10.dp))

                // 비밀번호 입력
                CustomTextField(value = password, onValueChange = { password = it }, placeholder = "비밀번호", isPassword = true)
                Spacer(modifier = Modifier.height(10.dp))

                // 가게명
                CustomTextField(value = storeName, onValueChange = { storeName = it }, placeholder = "가게명")
                Spacer(modifier = Modifier.height(10.dp))

                // 업종
                CustomTextField(value = businessType, onValueChange = { businessType = it }, placeholder = "업종")
                Spacer(modifier = Modifier.height(10.dp))

                // 휴대폰 번호
                CustomTextField(value = phoneNumber, onValueChange = { phoneNumber = it }, placeholder = "휴대폰 번호")
                Spacer(modifier = Modifier.height(10.dp))

                // 주소
                CustomTextField(value = address, onValueChange = { address = it }, placeholder = "주소")
                Spacer(modifier = Modifier.height(10.dp))

                // 대표 이미지 업로드
                Column(
                    modifier = Modifier.width(300.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "우리가게 대표 이미지", color = Color.Black, style = Regular20TextStyle)
                    Image(
                        painter = painterResource(id = R.drawable.img_upload),
                        contentDescription = "우리가게 대표 이미지 업로드하기",
                        modifier = Modifier
                            .size(70.dp)
                            .clickable(onClick = {
                                launcher.launch("image/*")
                            })
                    )
                }

                // 이미지 선택 후 미리보기
                imageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = "선택된 이미지",
                        modifier = Modifier.size(100.dp)
                    )
                }

                // 에러 메시지가 있을 때 화면에 출력
                errorMessage?.let { message ->
                    val displayMessage = when (message) {
                        "User with this email already exists." -> "이미 입점되었거나 승인 절차 중에 있는 가게입니다."
                        "Email and password are required" -> "아이디 혹은 비밀번호를 작성해주세요."
                        "StoreOwner creation failed" -> "입력란을 모두 채워주세요."
                        else -> message // 기타 에러 메시지 처리
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = displayMessage,
                        color = Color.Red,
                        style = Regular15TextStyle
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // 입점 신청서 제출 버튼
                CustomButton("입점 신청서 제출") {
                    viewModel.signUp(
                        id = id,
                        password = password,
                        storeName = storeName,
                        businessType = businessType,
                        phoneNumber = phoneNumber,
                        address = address
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Preview()
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = NavHostController(LocalContext.current))
}