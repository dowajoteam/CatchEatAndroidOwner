package catcheat.android.owner.ui.ranking.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import catcheat.android.owner.R
import catcheat.android.owner.ui.common.CheckSize1
import catcheat.android.owner.ui.common.CheckSize2
import catcheat.android.owner.ui.common.Leave
import catcheat.android.owner.ui.common.RestaurantInformation2
import catcheat.android.owner.ui.ranking.viewmodel.RankingDetailsViewModel
import catcheat.android.owner.ui.theme.LightCatchEat
import catcheat.android.owner.ui.theme.Medium15TextStyle
import catcheat.android.owner.ui.theme.Regular15TextStyle

@Composable
fun RankingDetailsScreen(navController: NavHostController, storeId: Int, viewModel: RankingDetailsViewModel = hiltViewModel()) {
    val rankingDetails by viewModel.rankingDetails.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // ViewModel에서 데이터 가져오기
    LaunchedEffect(storeId) {
        viewModel.fetchRankingDetails(storeId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (rankingDetails != null) {
            val details = rankingDetails!!

            Spacer(modifier = Modifier.height(50.dp))

            Leave(navController = navController, details.storeName, false)

            RestaurantInformation2(details.image, details.storeName, details.address)

            LazyColumn(
                modifier = Modifier.width(300.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    var isExpanded by remember { mutableStateOf(false) }

                    Spacer(modifier = Modifier.height(30.dp))

                    CheckSize1(
                        "물리적 접근성",
                        details.physicalAccessibility.allCriteriaMet,
                        details.physicalScore
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .fillMaxHeight()
                            .background(
                                color = LightCatchEat,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "출입문 간격 90cm 이상",
                                details.physicalAccessibility.doorWidthOver90cm
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "입구 경사로 또는 문턱 제거",
                                details.physicalAccessibility.rampOrNoThreshold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "자동문 또는 손잡이가 있는 문",
                                details.physicalAccessibility.automaticDoorOrHandle
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "가게 내에 휠체어 전용 공간 확보",
                                details.physicalAccessibility.wheelchairSpace
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "휠체어 내릴 수 있는 주차장 공간 확보",
                                details.physicalAccessibility.wheelchairParkingSpace
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2("장애인 전용 화장실", details.physicalAccessibility.disabledToilet)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.clickable { isExpanded = !isExpanded },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(if (isExpanded) R.drawable.toggle2 else R.drawable.toggle1),
                            contentDescription = if (isExpanded) {
                                "“${details.storeName}”에서 제공하는 기타 물리적 접근성 토글 닫기"
                            } else {
                                "“${details.storeName}”에서 제공하는 기타 물리적 접근성 토글 열기"
                            },
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "“${details.storeName}”에서 제공하는 기타 물리적 접근성",
                            style = Medium15TextStyle
                        )
                    }

                    // 클릭 시 표시되는 내용
                    AnimatedVisibility(visible = isExpanded) {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Column {
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타1", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타2", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타3", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타4", style = Regular15TextStyle)
                            }
                        }
                    }
                }
                item {
                    var isExpanded by remember { mutableStateOf(false) }

                    Spacer(modifier = Modifier.height(30.dp))

                    CheckSize1(
                        "서비스 접근성",
                        details.serviceAccessibility.allCriteriaMet,
                        details.serviceScore
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .fillMaxHeight()
                            .background(
                                color = LightCatchEat,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "직원 호출 주문 가능 여부",
                                details.serviceAccessibility.staffCallOrderAvailable
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.clickable { isExpanded = !isExpanded },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(if (isExpanded) R.drawable.toggle2 else R.drawable.toggle1),
                            contentDescription = if (isExpanded) {
                                "“${details.storeName}”에서 제공하는 기타 서비스 접근성 토글 닫기"
                            } else {
                                "“${details.storeName}”에서 제공하는 기타 서비스 접근성 토글 열기"
                            },
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "“${details.storeName}”에서 제공하는 기타 서비스 접근성",
                            style = Medium15TextStyle
                        )
                    }

                    // 클릭 시 표시되는 내용
                    AnimatedVisibility(visible = isExpanded) {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Column {
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타1", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타2", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타3", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타4", style = Regular15TextStyle)
                            }
                        }
                    }
                }
                item {
                    var isExpanded by remember { mutableStateOf(false) }

                    Spacer(modifier = Modifier.height(30.dp))

                    CheckSize1(
                        "시각장애인 지원",
                        details.visualImpairmentAccessibility.allCriteriaMet,
                        details.visualScore
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .width(300.dp)
                            .fillMaxHeight()
                            .background(
                                color = LightCatchEat,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "안내견 출입 가능 여부",
                                details.visualImpairmentAccessibility.guideDogAllowed
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "셀프 서비스 제공 여부",
                                details.visualImpairmentAccessibility.selfServiceAvailable
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            CheckSize2(
                                "중앙에 몰아서 테이블 배치 여부",
                                details.visualImpairmentAccessibility.centralTableSetup
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.clickable { isExpanded = !isExpanded },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(if (isExpanded) R.drawable.toggle2 else R.drawable.toggle1),
                            contentDescription = if (isExpanded) {
                                "“${details.storeName}”에서 제공하는 기타 서비스 접근성 토글 닫기"
                            } else {
                                "“${details.storeName}”에서 제공하는 기타 서비스 접근성 토글 열기"
                            },
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "“${details.storeName}”에서 제공하는 기타 서비스 접근성",
                            style = Medium15TextStyle
                        )
                    }

                    // 클릭 시 표시되는 내용
                    AnimatedVisibility(visible = isExpanded) {
                        Row {
                            Spacer(modifier = Modifier.width(20.dp))
                            Column {
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타1", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타2", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타3", style = Regular15TextStyle)
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = "기타4", style = Regular15TextStyle)
                            }
                        }
                    }
                }
            }
        }
    }
}