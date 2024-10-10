package catcheat.android.owner.ui.accessibility.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import catcheat.android.owner.ui.common.AccessibilityScreens
import catcheat.android.owner.ui.theme.Bold15TextStyle

@Composable
fun AccessibilityUploadNav(
    type: Int,
    currentItem: AccessibilityScreens,
    onTabSelected: (AccessibilityScreens) -> Unit
) {
    // 각 type에 맞는 필터링 된 스크린 목록 생성
    val filteredScreens = when (type) {
        0 -> AccessibilityScreens.entries.filter { it.name == "PhysicalRequired" || it.name == "PhysicalOther" }
        1 -> AccessibilityScreens.entries.filter { it.name == "ServiceRequired" || it.name == "ServiceOther" }
        2 -> AccessibilityScreens.entries.filter { it.name == "VisualRequired" || it.name == "VisualOther" }
        else -> AccessibilityScreens.entries
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // 필터링된 스크린 목록만 순회
            filteredScreens.forEach { screen ->
                val isSelected = currentItem == screen

                val color by animateColorAsState(
                    targetValue = if (isSelected) Color.Black else Color.Gray,
                    animationSpec = spring(), label = ""
                )

                // Column의 clickable 영역을 확장
                Column(
                    modifier = Modifier
                        .width(170.dp)  // 클릭 가능한 영역을 넓게 설정
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },  // 상호작용 상태
                            indication = null  // 리플 효과 제거
                        ) {
                            onTabSelected(screen)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = when (screen.name) {
                            "PhysicalRequired", "ServiceRequired", "VisualRequired" -> "필수 만족 항목"
                            "PhysicalOther", "ServiceOther", "VisualOther" -> "기타"
                            else -> screen.name
                        },
                        style = Bold15TextStyle,
                        color = color
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),  // Divider의 너비도 Column에 맞춤
                        thickness = 2.dp,
                        color = color
                    )
                }
            }
        }
    }
}



@Preview()
@Composable
fun AccessibilityUploadNavPreview() {
    AccessibilityUploadNav(0, AccessibilityScreens.PhysicalRequired) {}
}