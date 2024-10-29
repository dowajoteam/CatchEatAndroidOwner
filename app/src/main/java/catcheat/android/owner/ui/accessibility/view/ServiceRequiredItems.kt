package catcheat.android.owner.ui.accessibility.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import catcheat.android.owner.R
import catcheat.android.owner.ui.accessibility.viewmodel.ServiceRequiredItemsViewModel
import catcheat.android.owner.ui.common.AccessibilityUpload
import catcheat.android.owner.ui.common.CheckSize2
import catcheat.android.owner.ui.common.CheckSize3Bold
import catcheat.android.owner.ui.common.CustomButton
import catcheat.android.owner.ui.theme.Gray2

@Composable
fun ServiceRequiredItems(viewModel: ServiceRequiredItemsViewModel = hiltViewModel()) {
    val serviceRequiredItems by viewModel.serviceRequiredItems.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val uploadSuccess by viewModel.uploadSuccess.collectAsState()

    var staffCallOrderAvailableDescription by remember { mutableStateOf("") }
    var staffCallOrderAvailableImageUri by remember { mutableStateOf<Uri?>(null) }

    // ViewModel에서 데이터를 가져옴
    LaunchedEffect(Unit) {
        viewModel.fetchServiceRequiredItems()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        item {
            serviceRequiredItems?.let { items ->
                CheckSize3Bold("필수 만족 항목", items.staffCallOrderAvailable)

                Spacer(modifier = Modifier.height(10.dp))

                // 각 항목의 상태 표시
                CheckSize2("키오스크 매장 경우, 직원 호출 주문", items.staffCallOrderAvailable)
            } ?: run {
                // 데이터를 가져오는 중이거나 오류 발생 시
                if (errorMessage != null) {
                    Text(text = "Error: $errorMessage", color = Color.Red)
                } else {
                    CircularProgressIndicator()
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Gray2
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 이미지 선택 런처 설정
            val context = LocalContext.current
            val staffCallOrderAvailableLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri: Uri? ->
                    staffCallOrderAvailableImageUri = uri // 선택된 이미지를 저장
                }
            )

            // 사용자 입력을 통해 업로드할 설명 필드
            AccessibilityUpload(
                title = "키오스크 매장 경우, 직원 호출 주문",
                status = false,
                image = painterResource(id = R.drawable.ex_service1),
                imageUrl = null, // 업로드된 이미지 URL이 있는 경우 전달
                description = staffCallOrderAvailableDescription,
                selectedImageUri = staffCallOrderAvailableImageUri,
                onImageClick = {
                    staffCallOrderAvailableLauncher.launch("image/*")
                },
                onDescriptionChange = { newDesc ->
                    staffCallOrderAvailableDescription = newDesc
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 제출 버튼
            CustomButton(text = "제출하기", onClick = {
                viewModel.uploadServiceInfo(
                    staffCallOrderAvailableDescription,
                    staffCallOrderAvailableImageUri?.toFile(context)
                )
            })

            // 업로드 성공/실패 메시지
            uploadSuccess?.let { success ->
                if (success) {
                    Text(text = "업로드 성공!", color = Color.Green)
                } else {
                    Text(text = "업로드 실패!", color = Color.Red)
                }
            }
        }
    }
}