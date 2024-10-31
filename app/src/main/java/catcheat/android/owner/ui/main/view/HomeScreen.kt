package catcheat.android.owner.ui.main.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import catcheat.android.owner.R
import catcheat.android.owner.ui.common.AccessibilityScreens
import catcheat.android.owner.ui.common.AccessibilityUploadList
import catcheat.android.owner.ui.common.CustomerInfoBox
import catcheat.android.owner.ui.common.HomeRestaurantInfo
import catcheat.android.owner.ui.main.viewmodel.HomeViewModel
import catcheat.android.owner.ui.theme.BannerPoint
import catcheat.android.owner.ui.theme.Bold20TextStyle
import catcheat.android.owner.ui.theme.Medium15TextStyle
import catcheat.android.owner.ui.theme.Medium20TextStyle
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current

    val storeInfo by viewModel.storeInfo.observeAsState(null)
    val potentialCustomers by viewModel.potentialCustomers.observeAsState(null)
    val isRequestInProgress by viewModel.isRequestInProgress.observeAsState(false)
    val isImageUpdateInProgress by viewModel.isImageUpdateInProgress.observeAsState(false)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                val imageStream = context.contentResolver.openInputStream(uri)
                val requestBody = imageStream?.let { stream ->
                    MultipartBody.Part.createFormData(
                        "image",
                        "image.jpg",
                        stream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
                    )
                }
                requestBody?.let { viewModel.refreshImage(it) }
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        if (storeInfo == null && potentialCustomers == null && !isRequestInProgress) {
            viewModel.refresh()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.banner),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = "가게 홍보 기회 잡자 !",
                            style = Medium20TextStyle,
                            color = BannerPoint
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "접근성 카테고리별 기본 항목 충족 시\n캐칫 이용자에게 가게홍보가 진행됩니다.\n만족하는 카테고리를 늘리면 상위에 노출될 수 있어요.",
                            style = Medium15TextStyle,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                storeInfo?.let { info ->
                    HomeRestaurantInfo(
                        name = info.storeName,
                        address = info.address,
                        overallRank = info.overallRank,
                        totalRankingScore = info.totalRankingScore,
                        imageUrl = info.image,
                        onImageClick = {
                            if (!isImageUpdateInProgress) { // 이미지 업데이트 중이 아닐 때만 실행
                                launcher.launch("image/*")
                            }
                        }
                    )
                }
            }

            item {
                storeInfo?.let { info ->
                    potentialCustomers?.let { customers ->
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "우리가게 접근성 점수 올리러 가기",
                            style = Bold20TextStyle,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        CustomerInfoBox(info.storeName, customers.totalPotentialCustomers)
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

            item {
                storeInfo?.let { info ->
                    AccessibilityUploadList(0, info.physicalAccessibility, info.physicalScore) {
                        navController.navigate("${AccessibilityScreens.AccessibilityUpload.name}/0")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    AccessibilityUploadList(1, info.serviceAccessibility, info.serviceScore) {
                        navController.navigate("${AccessibilityScreens.AccessibilityUpload.name}/1")
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    AccessibilityUploadList(2, info.visualImpairmentAccessibility, info.visualScore) {
                        navController.navigate("${AccessibilityScreens.AccessibilityUpload.name}/2")
                    }

                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}
