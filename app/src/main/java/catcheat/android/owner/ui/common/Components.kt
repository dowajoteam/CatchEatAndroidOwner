package catcheat.android.owner.ui.common


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import catcheat.android.owner.R
import catcheat.android.owner.ui.ranking.view.RankingDetailsScreen
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
import coil3.compose.AsyncImagePainter.State.Empty.painter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.CachePolicy
import coil3.request.ImageRequest

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(50.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            textStyle = Regular15TextStyle.copy(color = Color.Black),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )

        if (value.isEmpty()) {
            Text(
                text = placeholder,
                color = Color.Gray,
                style = Regular15TextStyle
            )
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(330.dp)
            .height(50.dp)
            .background(color = CatchEat, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, style = Medium20TextStyle, color = Color.White)
    }
}
@Composable
fun CustomButton2(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true // enabled 매개변수 추가, 기본값은 true로 설정
) {
    Box(
        modifier = Modifier
            .width(330.dp)
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
    Row(
        modifier = Modifier
            .width(330.dp)
            .height(20.dp)
            .clickable {
                onStatusChange(!status)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))

        Image(
            painter = painterResource(
                id = if (status) R.drawable.lightcheck_o else R.drawable.lightcheck_x
            ),
            contentDescription = "$text2 동의하기 버튼",
            modifier = Modifier.size(15.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Row(
            modifier = Modifier.width(200.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "[$text1]", style = Bold15TextStyle, color = Color.Black)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "$text2", style = Regular15TextStyle, color = Color.Black)
        }

        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(id = R.drawable.lightenter),
            contentDescription = "$text2 동의하기 버튼",
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun RestaurantInformation1(
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
            contentDescription = "우리가게 대표 이미지",
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
fun RestaurantInformation2(imageUrl: String?, name: String, address: String) {
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
            contentDescription = "우리가게 대표 이미지",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = name, style = Bold25TextStyle)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = address, style = Regular15TextStyle, color = Color.Gray)
//            Spacer(modifier = Modifier.height(5.dp))
//            Text(text = "종합 랭킹 $ranking" + "위 ($score"+ "점)", style = Medium15TextStyle, color = CatchEat)
        }
    }
}

@Composable
fun CustomerInfoBox(name: String, missedCustomers: Int) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(50.dp)
            .background(CatchEat, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "현재 $name 사장님이\n놓치고 있는 고객은 $missedCustomers"+"명이에요.",
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
            .background(Color.White, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick // onClick이 NavController를 통해 동작하도록 변경
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
                    contentDescription = "$typeText 이미지",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            Column(modifier = Modifier.width(150.dp)) {
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
            Spacer(modifier = Modifier.width(30.dp))
            Image(
                painter = painterResource(id = R.drawable.enter),
                contentDescription = "$typeText 페이지 들어가기",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


@Composable
fun AccessibilityUpload(
    title: String,
    status: Boolean,
    image: Painter,
    imageUrl: String?,
    description: String,
    selectedImageUri: Uri?,
    onImageClick: () -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.width(300.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        CheckSize3Light(title, status)

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = image,
                    contentDescription = "$title 모범 사례",
                    modifier = Modifier
                        .width(145.dp)
                        .height(130.dp)
                )
                Text(
                    text = "모범사례 이미지",
                    style = Bold12TextStyle,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            // 가게 이미지 업로드
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .width(145.dp)
                        .height(130.dp)
                        .background(Gray1, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            onImageClick
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedImageUri != null) {
                        // 이미지 선택 여부에 따라 미리보기를 표시
                        Image(
                            painter = rememberAsyncImagePainter(selectedImageUri),
                            contentDescription = "$title 가게 이미지",
                            modifier = Modifier
                                .width(145.dp)
                                .height(130.dp)
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
                            modifier = Modifier
                                .width(145.dp)
                                .height(130.dp)
                                .clickable {
                                    onImageClick()
                                }
                        )
                    } else {
                        // 업로드된 이미지가 없는 경우
                        Text(
                            text = "사진 첨부하기",
                            style = Medium15TextStyle,
                            color = Color.Black,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
                Text(
                    text = "우리가게 이미지",
                    style = Bold12TextStyle,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }

        var text by remember { mutableStateOf(description) }
        var isFocused by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .width(300.dp)
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
        Spacer(modifier = Modifier.height(5.dp))
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
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(40.dp))

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .memoryCachePolicy(CachePolicy.DISABLED)  // 메모리 캐시 비활성화
                    .diskCachePolicy(CachePolicy.DISABLED)    // 디스크 캐시 비활성화
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = "$storeName 대표 이미지",
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
            contentDescription = contentDescriptionText,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = type,
            style = Bold12TextStyle,
            color = textColor
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

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = contentDescriptionText,
            modifier = Modifier.size(28.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = type,
            style = Medium20TextStyle,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(30.dp))

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
        modifier = Modifier.width(300.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(15.dp))

        Text(
            modifier = Modifier.width(250.dp),
            text = type,
            style = Regular15TextStyle,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(5.dp))

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = contentDescriptionText,
            modifier = Modifier.size(15.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))
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
        modifier = Modifier.width(300.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(15.dp))

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = contentDescriptionText,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            modifier = Modifier.width(250.dp),
            text = type,
            style = Bold15TextStyle,
            color = Color.Black
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
        modifier = Modifier.width(300.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(15.dp))

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = contentDescriptionText,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            modifier = Modifier.width(250.dp),
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
            .height(90.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
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
                contentDescription = "$name 페이지 나가기",
                modifier = Modifier.size(20.dp)
            )
        }

        if (title) {
            Text(
                modifier = Modifier.weight(1f),
                text = name,
                style = Bold20TextStyle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(90.dp))
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