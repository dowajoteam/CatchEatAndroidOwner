package catcheat.android.owner.ui.common

import android.net.Uri
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import catcheat.android.owner.R
import catcheat.android.owner.ui.theme.Bold12TextStyle
import catcheat.android.owner.ui.theme.Bold15TextStyle
import catcheat.android.owner.ui.theme.Bold20TextStyle
import catcheat.android.owner.ui.theme.Bold25TextStyle
import catcheat.android.owner.ui.theme.CatchEat
import catcheat.android.owner.ui.theme.ExtraBold20TextStyle
import catcheat.android.owner.ui.theme.Gray1
import catcheat.android.owner.ui.theme.Gray2
import catcheat.android.owner.ui.theme.Medium15TextStyle
import catcheat.android.owner.ui.theme.Medium20TextStyle
import catcheat.android.owner.ui.theme.Regular15TextStyle
import catcheat.android.owner.ui.theme.Regular20TextStyle
import coil3.compose.rememberAsyncImagePainter
import coil3.request.CachePolicy
import coil3.request.ImageRequest

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    isPhoneNumber: Boolean = false
) {
    val isFocused = remember { mutableStateOf(false) }
    val placeholderPosition by animateFloatAsState(if (value.isNotEmpty() || isFocused.value) -20f else 0f)
    val placeholderSize by animateFloatAsState(if (value.isNotEmpty() || isFocused.value) 15f else 20f)
    val placeholderColor = if (isFocused.value) CatchEat else Color.Gray
    val lineColor = if (isFocused.value) CatchEat else Color.Gray

    Box(
        modifier = Modifier
            .width(350.dp)
            .height(70.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                if (isPhoneNumber) {
                    // 숫자만 남기고 포맷팅하여 업데이트
                    val clean = newValue.replace(Regex("[^\\d]"), "")
                    onValueChange(clean) // 숫자만 유지
                } else {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .onFocusChanged {
                    isFocused.value = it.isFocused
                }
                .align(Alignment.CenterStart),
            singleLine = true,
            textStyle = Regular15TextStyle.copy(color = Color.Black),
            visualTransformation = if (isPassword) {
                PasswordVisualTransformation()
            } else if (isPhoneNumber) {
                PhoneNumberVisualTransformation()
            } else {
                VisualTransformation.None
            }
        )

        Text(
            text = placeholder,
            color = placeholderColor,
            style = Regular15TextStyle.copy(fontSize = placeholderSize.sp),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(y = placeholderPosition.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(lineColor)
                .align(Alignment.BottomCenter)
        )
    }
}
// 휴대폰 번호 포맷을 위한 VisualTransformation
class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val clean = text.text.replace(Regex("[^\\d]"), "")
        val formatted = StringBuilder()

        // 포맷팅된 전화번호 생성
        for (i in clean.indices) {
            if (i == 3 || i == 7) {
                formatted.append('-')
            }
            formatted.append(clean[i])
        }

        // 원본 텍스트의 길이와 포맷된 텍스트의 길이
        val originalLength = text.text.length
        val formattedLength = formatted.length

        // 사용자 정의 OffsetMapping
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset > originalLength) {
                    return formattedLength
                }

                var transformedOffset = 0
                for (i in 0 until offset) {
                    transformedOffset++
                    if (i == 3 || i == 7) {
                        transformedOffset++ // 하이픈 추가
                    }
                }
                return transformedOffset
            }

            override fun transformedToOriginal(offset: Int): Int {
                var originalOffset = 0
                var transformedCount = 0

                for (i in 0 until offset) {
                    originalOffset++
                    if (originalOffset <= clean.length && (originalOffset == 4 || originalOffset == 8)) {
                        transformedCount++ // 하이픈 무시
                    }
                    transformedCount++
                }

                return originalOffset.coerceAtMost(clean.length) // 범위를 벗어나지 않도록 조정
            }
        }

        return TransformedText(AnnotatedString(formatted.toString()), offsetMapping)
    }
}

@Composable
fun AddressTextField(
    address: String,
    onValueChange: (String) -> Unit,
) {
    val showWebView = remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(70.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    showWebView.value = true // 클릭 시 WebView를 보여줌
                }
        ) {
            Text(
                text = if (address.isNotEmpty()) address else "주소",
                color = if (address.isNotEmpty()) Color.Black else Color.Gray,
                style = if (address.isNotEmpty()) Regular15TextStyle else Regular20TextStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Gray)
                    .align(Alignment.BottomCenter)
            )
        }

        // WebView 표시 여부에 따라 WebView를 렌더링
        if (showWebView.value) {
            WebViewAddress(onAddressSelected = { selectedAddress ->
                onValueChange(selectedAddress)
                showWebView.value = false
            })
        }
    }
}

@Composable
fun WebViewAddress(
    onAddressSelected: (String) -> Unit
) {
    Box(modifier = Modifier
        .width(350.dp)
        .fillMaxHeight()) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()

                addJavascriptInterface(object {
                    @JavascriptInterface
                    fun processDATA(address: String) {
                        onAddressSelected(address) // 주소 선택 시 호출
                    }
                }, "Android")
                loadUrl("https://catcheat.s3.ap-northeast-2.amazonaws.com/map/index.html") // HTML 파일 URL
            }
        }, modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = if (enabled) CatchEat else Color.Gray, // enabled 상태에 따라 색상 변경
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .clickable(enabled = enabled) { // enabled에 따라 클릭 가능 여부 설정
                if (enabled) {
                    onClick()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = Medium20TextStyle, color = Color.White)
    }
}

@Composable
fun TOSCheck(text1: String, text2: String, status: Boolean, onStatusChange: (Boolean) -> Unit) {
    val statusDescription = if (status) "동의됨" else "동의되지 않음"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onStatusChange(!status)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.semantics { contentDescription = "$text1 $text2 동의하기 버튼 $statusDescription" }
        ) {
            Image(
                painter = painterResource(
                    id = if (status) R.drawable.lightcheck_o else R.drawable.lightcheck_x
                ),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "[$text1]", style = Bold15TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "$text2", style = Regular15TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
            }
        }
        Image(
            painter = painterResource(id = R.drawable.lightenter),
            contentDescription = "$text2 상세 페이지 들어가기",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun HomeRestaurantInfo(
    name: String,
    address: String,
    overallRank: String,
    totalRankingScore: Int,
    imageUrl: String?,
    onImageClick: () -> Unit
) {
    val rankText = remember(overallRank, totalRankingScore) {
        if (overallRank != "Not ranked") "종합 랭킹 $overallRank" + "위 ($totalRankingScore" + "점)" else ""
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .memoryCachePolicy(CachePolicy.DISABLED)  // 메모리 캐시 비활성화
                .diskCachePolicy(CachePolicy.DISABLED)    // 디스크 캐시 비활성화
                .build()
        )
        Image(
            painter = painter,
            contentDescription = "우리가게 대표 이미지 변경",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .clickable { onImageClick() },
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = name, style = Bold25TextStyle)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = address, style = Regular15TextStyle, color = Color.Gray)
            if (rankText.isNotEmpty()) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = rankText, style = Medium15TextStyle, color = CatchEat)
            }
        }
    }
}

@Composable
fun RestaurantInfo(imageUrl: String?, name: String, address: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .memoryCachePolicy(CachePolicy.DISABLED)  // 메모리 캐시 비활성화
                .diskCachePolicy(CachePolicy.DISABLED)    // 디스크 캐시 비활성화
                .build()
        )
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = name, style = Bold25TextStyle)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = address, style = Regular15TextStyle, color = Color.Gray)
        }
    }
}

@Composable
fun CustomerInfoBox(name: String, Customers: Int) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(50.dp)
            .background(CatchEat, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "현재 $name 사장님이\n놓치고 있는 고객은 $Customers"+"명이에요.",
            style = Regular15TextStyle,
            color = Color.White
        )
    }
}

@Composable
fun AccessibilityUploadList(type: Int, status: Boolean, score: Int, onClick: () -> Unit) {
    val (imageResource, typeText) = remember(type) {
        when (type) {
            0 -> R.drawable.physical to "물리적 접근성"
            1 -> R.drawable.service to "서비스 접근성"
            2 -> R.drawable.visual to "시각장애인 지원"
            else -> null to "알 수 없는 접근성"
        }
    }

    val rankingStatus = remember(status) {
        if (status) "접근성 랭킹 등재 완료!" else ""
    }

    Box(
        modifier = Modifier
            .wrapContentWidth()
            .background(Color.White)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            imageResource?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier.width(170.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = typeText,
                        color = Color.Black,
                        style = Medium20TextStyle
                    )
                    if (rankingStatus.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "$score"+"점",
                            color = CatchEat,
                            style = Bold12TextStyle
                        )
                    }
                }
                if (rankingStatus.isNotEmpty()) {
                    Text(
                        text = rankingStatus,
                        color = Color.Green,
                        style = Regular15TextStyle
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(id = R.drawable.enter),
                contentDescription = "$typeText 페이지 들어가기",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


@Composable
fun PhysicalUpload(
    title: String,
    status: Boolean,
    image: Painter,
    imageUrl: String?,
    description: String?,
    selectedImageUri: Uri?,
    onImageClick: () -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        CheckSize3Light(title, status)

        Spacer(modifier = Modifier.height(15.dp))

        Row {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Text(
                    text = "모범사례 이미지",
                    style = Bold12TextStyle,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)
                        .clearAndSetSemantics {}
                )
            }
            // 가게 이미지 업로드
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(Gray1, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            onImageClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedImageUri != null) {
                        // 이미지 선택 여부에 따라 미리보기를 표시
                        Image(
                            painter = rememberAsyncImagePainter(selectedImageUri),
                            contentDescription = "$title 가게 이미지",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    onImageClick()
                                }
                        )
                    } else if (imageUrl != null) {
                        // 업로드된 이미지가 있는 경우
                        val painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imageUrl)
                                .memoryCachePolicy(CachePolicy.DISABLED)  // 메모리 캐시 비활성화
                                .diskCachePolicy(CachePolicy.DISABLED)    // 디스크 캐시 비활성화
                                .build()
                        )
                        Image(
                            painter = painter,
                            contentDescription = "$title 가게 이미지",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    onImageClick()
                                }
                        )
                    } else {
                        // 업로드된 이미지가 없는 경우
                        Text(
                            text = "사진 첨부하기 (선택)",
                            style = Medium15TextStyle,
                            color = Color.Black
                        )
                    }
                }
                Text(
                    text = "우리가게 이미지",
                    style = Bold12TextStyle,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)
                        .clearAndSetSemantics {}
                )
            }
        }

        var text by remember { mutableStateOf(description ?: "") }
        var isFocused by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray1, RoundedCornerShape(10.dp))
                .padding(10.dp)
                .clickable { isFocused = true }
        ) {
            BasicTextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                    onDescriptionChange(newText) // 설명이 변경되면 ViewModel로 전달
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textStyle = Regular15TextStyle.copy(color = Color.Black),
                singleLine = false,
                decorationBox = { innerTextField ->
                    if (text.isEmpty() && !isFocused) {
                        Text(
                            text = "설명하고 싶은 부분을 자유롭게 작성해주세요.",
                            style = Regular15TextStyle,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}
@Composable
fun ServiceVisualUpload(
    title: String,
    status: Boolean,
    imageUrl: String?,
    description: String?,
    selectedImageUri: Uri?,
    onImageClick: () -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        CheckSize3Light(title, status)

        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Gray1, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onImageClick()
                },
            contentAlignment = Alignment.Center
        ) {
            if (selectedImageUri != null) {
                // 이미지 선택 여부에 따라 미리보기를 표시
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "$title 가게 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onImageClick()
                        }
                )
            } else if (imageUrl != null) {
                // 업로드된 이미지가 있는 경우
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .memoryCachePolicy(CachePolicy.DISABLED)  // 메모리 캐시 비활성화
                        .diskCachePolicy(CachePolicy.DISABLED)    // 디스크 캐시 비활성화
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = "$title 가게 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onImageClick()
                        }
                )
            } else {
                // 업로드된 이미지가 없는 경우
                Text(
                    text = "사진 첨부하기 (선택)",
                    style = Medium15TextStyle,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        var text by remember { mutableStateOf(description ?: "") }
        var isFocused by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray1, RoundedCornerShape(10.dp))
                .padding(10.dp)
                .clickable { isFocused = true }
        ) {
            BasicTextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                    onDescriptionChange(newText) // 설명이 변경되면 ViewModel로 전달
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                textStyle = Regular15TextStyle.copy(color = Color.Black),
                singleLine = false,
                decorationBox = { innerTextField ->
                    if (text.isEmpty() && !isFocused) {
                        Text(
                            text = "설명하고 싶은 부분을 자유롭게 작성해주세요.",
                            style = Regular15TextStyle,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Composable
fun RankingList(
    rank: Int,
    storeId: Int,
    imageUrl: String?,
    storeName: String,
    physicalAccessibility: Boolean,
    serviceAccessibility: Boolean,
    visualImpairmentAccessibility: Boolean,
    navController: NavHostController
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            navController.navigate("${RankingDetailsScreens.RankingDetails.name}/$storeId")
        },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .memoryCachePolicy(CachePolicy.DISABLED)  // 메모리 캐시 비활성화
                    .diskCachePolicy(CachePolicy.DISABLED)    // 디스크 캐시 비활성화
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.width(30.dp))

            Column {
                Text(
                    text = "$rank" + "위 $storeName",
                    style = ExtraBold20TextStyle,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))
                RankingListCheck("물리적 접근성", physicalAccessibility)
                Spacer(modifier = Modifier.height(10.dp))
                RankingListCheck("서비스 접근성", serviceAccessibility)
                Spacer(modifier = Modifier.height(10.dp))
                RankingListCheck("시각장애인 지원", visualImpairmentAccessibility)
            }
        }
    }
}

@Composable
fun RankingListCheck(type: String, status: Boolean) {
    // status 값이 "충족"일 때의 값들을 한번에 설정
    val isFulfilled = status == true

    val (imageResource, contentDescriptionText, textColor) = if (isFulfilled) {
        Triple(R.drawable.check_o, "$type 필수 항목 충족", Color.Black)
    } else {
        Triple(R.drawable.check_x, "$type 필수 항목 미충족", Gray2)
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "$contentDescriptionText",
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = type,
            style = Bold12TextStyle,
            color = textColor,
            modifier = Modifier.clearAndSetSemantics {}
        )
    }
}

@Composable
fun CheckSize1(type: String, status: Boolean, score: Int) {
    // status 값이 "충족"일 때의 값들을 한번에 설정
    val isFulfilled = status == true

    val (imageResource, contentDescriptionText) = if (isFulfilled) {
        Pair(R.drawable.check_o, "$type 필수 항목 충족")
    } else {
        Pair(R.drawable.check_x, "$type 필수 항목 미충족")
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.semantics { contentDescription = "$contentDescriptionText" }
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = type,
            style = Medium20TextStyle,
            color = Color.Black,
            modifier = Modifier.clearAndSetSemantics {}
        )
        Spacer(modifier = Modifier.width(10.dp))
        // "충족" 상태일 때만 점수 표시
        if (isFulfilled) {
            Text(
                text = "$score"+"점",
                style = Medium20TextStyle,
                color = CatchEat
            )
        }
    }
}

@Composable
fun CheckSize2(type: String, status: Boolean) {
    // status 값이 "충족"일 때의 값들을 한번에 설정
    val isFulfilled = status == true

    val (imageResource, contentDescriptionText) = if (isFulfilled) {
        Pair(R.drawable.lightcheck_o, "$type 항목 충족")
    } else {
        Pair(R.drawable.lightcheck_x, "$type 항목 미충족")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "$contentDescriptionText" },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = type,
                style = Regular15TextStyle,
                color = Color.Black,
                modifier = Modifier.clearAndSetSemantics {}
            )
        }
        Row {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun CheckSize3Bold(type: String, status: Boolean) {
// status 값이 "충족"일 때의 값들을 한번에 설정
    val isFulfilled = status == true

    val (imageResource, contentDescriptionText) = if (isFulfilled) {
        Pair(R.drawable.check_o, "$type 충족")
    } else {
        Pair(R.drawable.check_x, "$type 미충족")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "$contentDescriptionText" },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(15.dp))
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = type,
            style = Bold15TextStyle,
            color = Color.Black,
            modifier = Modifier.clearAndSetSemantics {}
        )
    }
}

@Composable
fun CheckSize3Light(type: String, status: Boolean) {
// status 값이 "충족"일 때의 값들을 한번에 설정
    val isFulfilled = status == true

    val (imageResource, contentDescriptionText) = if (isFulfilled) {
        Pair(R.drawable.check_o, "$type 충족")
    } else {
        Pair(R.drawable.check_x, "$type 미충족")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "$contentDescriptionText" },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(15.dp))

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            modifier = Modifier
                .width(250.dp)
                .clearAndSetSemantics {},
            text = type,
            style = Medium15TextStyle,
            color = Color.Black
        )
    }
}



@Composable
fun Leave(navController: NavHostController, name: String, title: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .semantics { contentDescription = "$name 페이지 나가기" }
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },  // 상호작용 상태
                    indication = null  // 리플 효과 제거
                ) {
                    // 이전 화면으로 돌아가는 동작
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.leave),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
        if (title) {
            Text(
                text = name,
                style = Bold20TextStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier.clearAndSetSemantics {}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ComponentsPriview() {
    Column {
//        RestaurantInformation("위플랜트위커피", "서울 노원구 동일로174길 7 1층", 1, 24)
//        Leave("물리적 접근성", true)
//        RankingList(1, "솔직하다", 1, 5, 1, 4, 0, 0)
//        CheckSize1("물리적 접근성", 1, 20)
//        CheckSize2("키오스크 매장 경우, 직원 호출 주문 가능", 1)
//        AccessibilityUploadList(2, 0)
//        CheckSize3Bold("물리적 접근성", 0)
//        CheckSize3Light("물리적 접근성", 0)
    }
}