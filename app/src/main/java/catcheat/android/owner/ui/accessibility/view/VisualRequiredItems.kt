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
import catcheat.android.owner.ui.accessibility.viewmodel.VisualRequiredItemsViewModel
import catcheat.android.owner.ui.common.AccessibilityUpload
import catcheat.android.owner.ui.common.CheckSize2
import catcheat.android.owner.ui.common.CheckSize3Bold
import catcheat.android.owner.ui.common.CustomButton
import catcheat.android.owner.ui.theme.Gray2

@Composable
fun VisualRequiredItems(viewModel: VisualRequiredItemsViewModel = hiltViewModel()) {
    val visualRequiredItems by viewModel.visualRequiredItems.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val uploadSuccess by viewModel.uploadSuccess.collectAsState()

    var guideDogAllowedDescription by remember { mutableStateOf("") }
    var guideDogAllowedImage by remember { mutableStateOf<Uri?>(null) }
    var selfServiceAvailableDescription by remember { mutableStateOf("") }
    var selfServiceAvailableImage by remember { mutableStateOf<Uri?>(null) }
    var centralTableSetupDescription by remember { mutableStateOf("") }
    var centralTableSetupImage by remember { mutableStateOf<Uri?>(null) }

    // ViewModel에서 데이터를 가져옴
    LaunchedEffect(Unit) {
        viewModel.fetchVisualRequiredItems()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        item {
            visualRequiredItems?.let { items ->
                CheckSize3Bold("필수 만족 항목", listOf(
                    items.guideDogAllowed,
                    items.selfServiceAvailable,
                    items.centralTableSetup
                ).all { it })

                Spacer(modifier = Modifier.height(10.dp))

                // 각 항목의 상태 표시
                CheckSize2("안내견 출입", items.guideDogAllowed)
                Spacer(modifier = Modifier.height(10.dp))
                CheckSize2("셀프 서비스 제공", items.selfServiceAvailable)
                Spacer(modifier = Modifier.height(10.dp))
                CheckSize2("중앙에 몰아서 테이블 세팅", items.centralTableSetup)
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

            // 사용자 입력을 통해 업로드할 설명 필드
            val guideDogAllowedLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri: Uri? ->
                    guideDogAllowedImage = uri // 선택된 이미지를 저장
                }
            )
            AccessibilityUpload(
                title = "안내견 출입",
                status = visualRequiredItems?.guideDogAllowed ?: false,
                image = painterResource(id = R.drawable.ex_visual1),
                imageUrl = null,
                description = guideDogAllowedDescription,
                selectedImageUri = guideDogAllowedImage,
                onImageClick = {
                    guideDogAllowedLauncher.launch("image/*")
                },
                onDescriptionChange = { newDesc -> guideDogAllowedDescription = newDesc }
            )

            val selfServiceAvailableLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri: Uri? ->
                    selfServiceAvailableImage = uri // 선택된 이미지를 저장
                }
            )
            AccessibilityUpload(
                title = "셀프 서비스 제공",
                status = visualRequiredItems?.selfServiceAvailable ?: false,
                image = painterResource(id = R.drawable.ex_visual2),
                imageUrl = null,
                description = selfServiceAvailableDescription,
                selectedImageUri = selfServiceAvailableImage,
                onImageClick = {
                    selfServiceAvailableLauncher.launch("image/*")
                },
                onDescriptionChange = { newDesc -> selfServiceAvailableDescription = newDesc }
            )

            val centralTableSetupLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri: Uri? ->
                    centralTableSetupImage = uri // 선택된 이미지를 저장
                }
            )
            AccessibilityUpload(
                title = "중앙에 몰아서 테이블 세팅",
                status = visualRequiredItems?.centralTableSetup ?: false,
                image = painterResource(id = R.drawable.ex_visual3),
                imageUrl = null,
                description = centralTableSetupDescription,
                selectedImageUri = centralTableSetupImage,
                onImageClick = {
                    centralTableSetupLauncher.launch("image/*")
                },
                onDescriptionChange = { newDesc -> centralTableSetupDescription = newDesc }
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 제출 버튼
            val context = LocalContext.current
            CustomButton(text = "제출하기", onClick = {
                viewModel.uploadVisualInfo(
                    guideDogAllowedDescription = guideDogAllowedDescription,
                    guideDogAllowedImageFile = guideDogAllowedImage?.toFile(context),
                    selfServiceAvailableDescription = selfServiceAvailableDescription,
                    selfServiceAvailableImageFile = selfServiceAvailableImage?.toFile(context),
                    centralTableSetupDescription = centralTableSetupDescription,
                    centralTableSetupImageFile = centralTableSetupImage?.toFile(context)
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
