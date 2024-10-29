package catcheat.android.owner.ui.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import catcheat.android.owner.ui.common.RankingScreens
import catcheat.android.owner.ui.ranking.view.EntireRanking
import catcheat.android.owner.ui.ranking.view.PhysicalRanking
import catcheat.android.owner.ui.ranking.view.RankingNav
import catcheat.android.owner.ui.ranking.view.ServiceRanking
import catcheat.android.owner.ui.ranking.view.VisualRanking
import catcheat.android.owner.ui.theme.CatchEat
import catcheat.android.owner.ui.theme.Medium15TextStyle

@Composable
fun RankingScreen(navController: NavHostController) {
    var currentRanking by remember { mutableStateOf(RankingScreens.total) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 상단 설명 텍스트
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = "접근성 점수를 기반으로 한 사장님들의 순위에요." },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "접근성 점수", style = Medium15TextStyle, color = CatchEat, modifier = Modifier.clearAndSetSemantics {})
            Text(text = "를 기반으로 한 사장님들의 순위이에요.", style = Medium15TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
        }

        Spacer(modifier = Modifier.height(15.dp))

        // RankingNav - 카테고리별 네비게이션
        RankingNav(currentRanking = currentRanking, onTabSelected = {
            currentRanking = it
            // 상태 업데이트만 수행하여 화면 내에서 카테고리 변경
        })

        Spacer(modifier = Modifier.height(20.dp))

        // 선택된 카테고리에 따라 다른 콘텐츠 표시
        when (currentRanking) {
            RankingScreens.total -> EntireRanking(navController)
            RankingScreens.physical -> PhysicalRanking(navController)
            RankingScreens.service -> ServiceRanking(navController)
            RankingScreens.visual -> VisualRanking(navController)
        }
    }
}
