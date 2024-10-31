package catcheat.android.owner.ui.accessibility.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import catcheat.android.owner.ui.accessibility.viewmodel.PhysicalRequiredItemsViewModel
import catcheat.android.owner.ui.common.CheckSize2
import catcheat.android.owner.ui.common.CheckSize3Bold
import catcheat.android.owner.ui.common.CustomButton
import catcheat.android.owner.ui.common.PhysicalUpload
import catcheat.android.owner.ui.theme.Gray2

@Composable
fun PhysicalRequiredItems(viewModel: PhysicalRequiredItemsViewModel = hiltViewModel()) {
    val physicalRequiredItems by viewModel.physicalRequiredItems.collectAsState()
    val physicalInfo by viewModel.physicalInfo.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val uploadSuccess by viewModel.uploadSuccess.collectAsState()

    var doorWidthOver90cmDescription by remember { mutableStateOf("") }
    var rampOrNoThresholdDescription by remember { mutableStateOf("") }
    var automaticDoorOrHandleDescription by remember { mutableStateOf("") }
    var wheelchairSpaceDescription by remember { mutableStateOf("") }
    var wheelchairParkingSpaceDescription by remember { mutableStateOf("") }
    var disabledToiletDescription by remember { mutableStateOf("") }

    var doorWidthOver90cmImage by remember { mutableStateOf<Uri?>(null) }
    var rampOrNoThresholdImage by remember { mutableStateOf<Uri?>(null) }
    var automaticDoorOrHandleImage by remember { mutableStateOf<Uri?>(null) }
    var wheelchairSpaceImage by remember { mutableStateOf<Uri?>(null) }
    var wheelchairParkingSpaceImage by remember { mutableStateOf<Uri?>(null) }
    var disabledToiletImage by remember { mutableStateOf<Uri?>(null) }

    // ViewModel에서 데이터를 가져옴
    LaunchedEffect(Unit) {
        viewModel.fetchPhysicalInfoAndRequiredItems()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            physicalRequiredItems?.let { items ->
                physicalInfo?.let { info ->

                    CheckSize3Bold(
                        "필수 만족 항목", listOf(
                            items.doorWidthOver90cm,
                            items.rampOrNoThreshold,
                            items.automaticDoorOrHandle,
                            items.wheelchairSpace,
                            items.wheelchairParkingSpace,
                            items.disabledToilet
                        ).all { it }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // 각 항목의 상태 표시
                    CheckSize2("출입문 간격 90cm 이상", items.doorWidthOver90cm)
                    Spacer(modifier = Modifier.height(10.dp))
                    CheckSize2("입구 경사로 또는 문턱 제거", items.rampOrNoThreshold)
                    Spacer(modifier = Modifier.height(10.dp))
                    CheckSize2("자동문 또는 손잡이가 있는 문", items.automaticDoorOrHandle)
                    Spacer(modifier = Modifier.height(10.dp))
                    CheckSize2("가게 내에 휠체어 전용 공간 확보", items.wheelchairSpace)
                    Spacer(modifier = Modifier.height(10.dp))
                    CheckSize2("휠체어 내릴 수 있는 주차장 공간 확보", items.wheelchairParkingSpace)
                    Spacer(modifier = Modifier.height(10.dp))
                    CheckSize2("장애인 전용 화장실", items.disabledToilet)

                    Spacer(modifier = Modifier.height(20.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Gray2
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // 사용자 입력을 통해 업로드할 설명 필드들 + 이미지 선택 추가
                    val doorWidthOver90cmLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { uri: Uri? ->
                            doorWidthOver90cmImage = uri // 선택된 이미지를 저장
                        }
                    )
                    PhysicalUpload(
                        "출입문 간격 90cm 이상",
                        items.doorWidthOver90cm,
                        painterResource(id = R.drawable.ex_physical1),
                        info.doorWidthOver90cmImage,
                        info.doorWidthOver90cmDescription,
                        selectedImageUri = doorWidthOver90cmImage,
                        onImageClick = {
                            doorWidthOver90cmLauncher.launch("image/*")
                        },
                        onDescriptionChange = { newDesc -> doorWidthOver90cmDescription = newDesc }
                    )
                    val rampOrNoThresholdLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { uri: Uri? ->
                            rampOrNoThresholdImage = uri // 선택된 이미지를 저장
                        }
                    )
                    PhysicalUpload(
                        "입구 경사로 또는 문턱 제거",
                        items.rampOrNoThreshold,
                        painterResource(id = R.drawable.ex_physical2),
                        info.rampOrNoThresholdImage,
                        info.rampOrNoThresholdDescription,
                        selectedImageUri = rampOrNoThresholdImage,
                        onImageClick = {
                            rampOrNoThresholdLauncher.launch("image/*")
                        },
                        onDescriptionChange = { newDesc -> rampOrNoThresholdDescription = newDesc }
                    )
                    val automaticDoorOrHandleLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { uri: Uri? ->
                            automaticDoorOrHandleImage = uri // 선택된 이미지를 저장
                        }
                    )
                    PhysicalUpload(
                        "자동문 또는 손잡이가 있는 문",
                        items.automaticDoorOrHandle,
                        painterResource(id = R.drawable.ex_physical3),
                        info.automaticDoorOrHandleImage,
                        info.automaticDoorOrHandleDescription,
                        selectedImageUri = automaticDoorOrHandleImage,
                        onImageClick = {
                            automaticDoorOrHandleLauncher.launch("image/*")
                        },
                        onDescriptionChange = { newDesc -> automaticDoorOrHandleDescription = newDesc }
                    )
                    val wheelchairSpaceLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { uri: Uri? ->
                            wheelchairSpaceImage = uri // 선택된 이미지를 저장
                        }
                    )
                    PhysicalUpload(
                        "가게 내에 휠체어 전용 공간 확보",
                        items.wheelchairSpace,
                        painterResource(id = R.drawable.ex_physical4),
                        info.wheelchairSpaceImage,
                        info.wheelchairSpaceDescription,
                        selectedImageUri = wheelchairSpaceImage,
                        onImageClick = {
                            wheelchairSpaceLauncher.launch("image/*")
                        },
                        onDescriptionChange = { newDesc -> wheelchairSpaceDescription = newDesc }
                    )
                    val wheelchairParkingSpaceLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { uri: Uri? ->
                            wheelchairParkingSpaceImage = uri // 선택된 이미지를 저장
                        }
                    )
                    PhysicalUpload(
                        "휠체어 내릴 수 있는 주차장 공간 확보",
                        items.wheelchairParkingSpace,
                        painterResource(id = R.drawable.ex_physical5),
                        info.wheelchairParkingSpaceImage,
                        info.wheelchairParkingSpaceDescription,
                        selectedImageUri = wheelchairParkingSpaceImage,
                        onImageClick = {
                            wheelchairParkingSpaceLauncher.launch("image/*")
                        },
                        onDescriptionChange = { newDesc -> wheelchairParkingSpaceDescription = newDesc }
                    )
                    val disabledToiletLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = { uri: Uri? ->
                            disabledToiletImage = uri // 선택된 이미지를 저장
                        }
                    )
                    PhysicalUpload(
                        "장애인 전용 화장실",
                        items.disabledToilet,
                        painterResource(id = R.drawable.ex_physical6),
                        info.disabledToiletImage,
                        info.disabledToiletDescription,
                        selectedImageUri = disabledToiletImage,
                        onImageClick = {
                            disabledToiletLauncher.launch("image/*")
                        },
                        onDescriptionChange = { newDesc -> disabledToiletDescription = newDesc }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    // 제출 버튼
                    val context = LocalContext.current
                    CustomButton("제출하기", onClick = {
                        viewModel.uploadPhysicalInfo(
                            doorWidthOver90cmDescription = doorWidthOver90cmDescription,
                            doorWidthOver90cmImageFile = doorWidthOver90cmImage?.toFile(context),
                            rampOrNoThresholdDescription = rampOrNoThresholdDescription,
                            rampOrNoThresholdImageFile = rampOrNoThresholdImage?.toFile(context),
                            automaticDoorOrHandleDescription = automaticDoorOrHandleDescription,
                            automaticDoorOrHandleImageFile = automaticDoorOrHandleImage?.toFile(context),
                            wheelchairSpaceDescription = wheelchairSpaceDescription,
                            wheelchairSpaceImageFile = wheelchairSpaceImage?.toFile(context),
                            wheelchairParkingSpaceDescription = wheelchairParkingSpaceDescription,
                            wheelchairParkingSpaceImageFile = wheelchairParkingSpaceImage?.toFile(context),
                            disabledToiletDescription = disabledToiletDescription,
                            disabledToiletImageFile = disabledToiletImage?.toFile(context)
                        )
                    })

                    uploadSuccess?.let { success ->
                        if (success) {
                            Text(text = "업로드 성공!", color = Color.Green)
                        } else {
                            Text(text = "업로드 실패", color = Color.Red)
                        }
                    }
                }
            } ?: run {
                if (errorMessage != null) {
                    Text(text = "Error: $errorMessage", color = Color.Red)
                } else {
                    CircularProgressIndicator()
                }
            }
        }
    }
}