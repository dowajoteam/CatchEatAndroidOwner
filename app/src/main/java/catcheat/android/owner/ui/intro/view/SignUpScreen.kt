package catcheat.android.owner.ui.intro.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import catcheat.android.owner.R
import catcheat.android.owner.ui.common.AddressTextField
import catcheat.android.owner.ui.common.CustomButton
import catcheat.android.owner.ui.common.CustomTextField
import catcheat.android.owner.ui.common.IntroScreens
import catcheat.android.owner.ui.common.Leave
import catcheat.android.owner.ui.intro.viewmodel.SignUpViewModel
import catcheat.android.owner.ui.theme.Bold30TextStyle
import catcheat.android.owner.ui.theme.CatchEat
import catcheat.android.owner.ui.theme.Regular15TextStyle
import catcheat.android.owner.ui.theme.Regular20TextStyle
import coil3.compose.rememberAsyncImagePainter
import org.json.JSONObject

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    var storeName by remember { mutableStateOf("") }
    var businessType by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val signUpResponse by viewModel.signUpResponse.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // 아이디 및 비밀번호 유효성 검사 통과 여부
    val isIdValid = remember(id) { id.isNotBlank() && viewModel.isIdValid(id) }
    val isPasswordValid = remember(password) { password.isNotBlank() && viewModel.isPasswordValid(password) }
    val isPasswordMatching = password == passwordConfirm

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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Leave(navController = navController, "입점 신청", false)
        }

        Column {
            Row(
                modifier = Modifier.semantics { contentDescription = "캐칫 사장님 입점 신청" },
            ) {
                Text(text = "캐칫 사장님", style = Bold30TextStyle, color = CatchEat, modifier = Modifier.clearAndSetSemantics {})
                Text(text = " 입점 신청", style = Bold30TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
            }

            Spacer(modifier = Modifier.height(30.dp))

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item {
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

                    // 비밀번호 확인 입력
                    CustomTextField(
                        value = passwordConfirm,
                        onValueChange = { passwordConfirm = it },
                        placeholder = "비밀번호 확인",
                        isPassword = true
                    )
                    if (!isPasswordMatching && passwordConfirm.isNotBlank()) {
                        Text(
                            text = "비밀번호가 일치하지 않습니다.",
                            color = Color.Red,
                            style = Regular15TextStyle,
                            modifier = Modifier
                                .width(350.dp)
                                .height(15.dp)
                        )
                    } else {
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                    // 가게명
                    CustomTextField(value = storeName, onValueChange = { storeName = it }, placeholder = "가게명")
                    Spacer(modifier = Modifier.height(15.dp))

                    // 업종
                    CustomTextField(value = businessType, onValueChange = { businessType = it }, placeholder = "업종")
                    Spacer(modifier = Modifier.height(15.dp))

                    // 업주 휴대폰 번호
                    CustomTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        placeholder = "업주 휴대폰 번호",
                        isPhoneNumber = true
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    // 주소 입력
                    AddressTextField(address = address, onValueChange = { address = it })
                    Spacer(modifier = Modifier.height(30.dp))

                    // 대표 이미지 업로드
                    Column(
                        modifier = Modifier.width(350.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = "우리가게 대표 이미지", color = Color.Black, style = Regular20TextStyle, modifier = Modifier.clearAndSetSemantics {})

                        // 이미지 업로드 버튼 또는 선택된 이미지 미리보기
                        if (imageUri == null) {
                            Image(
                                painter = painterResource(id = R.drawable.img_upload),
                                contentDescription = "우리가게 대표 이미지 업로드하기",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            launcher.launch("image/*")
                                        })
                            )
                        } else {
                            // 이미지 선택 후 미리보기
                            Image(
                                painter = rememberAsyncImagePainter(imageUri),
                                contentDescription = "선택된 이미지",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                        launcher.launch("image/*")
                                    })
                            )
                        }
                    }

                    // 회원가입 결과 처리
                    LaunchedEffect(signUpResponse, errorMessage) {
                        signUpResponse?.let { response ->
                            if (response != null) {
                                // signUpResponse가 null이 아니고, 에러 메시지가 없는 경우 성공 처리
                                navController.navigate(IntroScreens.SignUpCompleted.name)
                            }
                        }
                    }
                    // 에러 메시지가 있을 때 화면에 출력
                    if (errorMessage != null) {
                        // JSON 파싱
                        val errorJson = try {
                            JSONObject(errorMessage ?: "") // errorMessage가 null일 수 있으므로 기본값 빈 문자열
                        } catch (e: Exception) {
                            null // 파싱 실패 시 null 처리
                        }

                        // "error" 필드의 값을 추출하고 null일 경우 기본 메시지로 설정
                        val errorText: String = errorJson?.optString("error") ?: (errorMessage ?: "")

                        // 한글 메시지로 변환
                        val displayMessage = when (errorText) {
                            "User with this email already exists." -> "이미 입점되었거나 승인 절차 중에 있는 가게입니다."
                            "Email and password are required" -> "아이디 혹은 비밀번호를 작성해주세요."
                            "StoreOwner creation failed" -> "입력란을 모두 채워주세요."
                            else -> errorText
                        }

                        // 에러 메시지 출력
                        Text(
                            text = displayMessage,
                            color = Color.Red,
                            style = Regular15TextStyle,
                            modifier = Modifier
                                .width(350.dp)
                                .height(50.dp)
                        )
                    } else {
                        Spacer(modifier = Modifier.height(50.dp))
                    }

                    // 입점 신청서 제출
                    CustomButton(
                        text = "입점 신청서 제출",
                        onClick = {
                            // 클릭 시 상태 초기화
                            viewModel.clearSignUpState()  // 상태 초기화 함수 호출
                            viewModel.signUp(
                                id = id,
                                password = password,
                                storeName = storeName,
                                businessType = businessType,
                                phoneNumber = phoneNumber,
                                address = address
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}