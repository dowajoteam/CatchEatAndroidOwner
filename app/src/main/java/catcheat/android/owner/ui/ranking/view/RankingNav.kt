package catcheat.android.owner.ui.ranking.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import catcheat.android.owner.R
import catcheat.android.owner.ui.common.MainScreens
import catcheat.android.owner.ui.common.RankingScreens
import catcheat.android.owner.ui.main.view.MainNav
import catcheat.android.owner.ui.theme.Bold15TextStyle
import catcheat.android.owner.ui.theme.CatchEat

@Composable
fun RankingNav(
    currentRanking: RankingScreens,
    onTabSelected: (RankingScreens) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RankingScreens.entries.forEach { screen ->
            val isSelected = currentRanking == screen

            val color by animateColorAsState(
                targetValue = if (isSelected) CatchEat else Color.Gray,
                animationSpec = spring(), label = ""
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pointcircle),
                    contentDescription = "",
                    modifier = Modifier.alpha(if (isSelected) 1f else 0f)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = when (screen.name) {
                        "total" -> "종합"
                        "physical" -> "물리적"
                        "service" -> "서비스"
                        "visual" -> "시각장애인 지원"
                        else -> ""
                    },
                    style = Bold15TextStyle,
                    color = color,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .semantics {
                            contentDescription = when (screen.name) {
                                "total" -> "종합 랭킹"
                                "physical" -> "물리적 접근성 랭킹"
                                "service" -> "서비스 접근성 랭킹"
                                "visual" -> "시각장애인 지원 접근성 랭킹"
                                else -> ""
                            }
                        }
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onTabSelected(screen)
                        }
                )
            }
        }
    }
}

@Preview()
@Composable
fun RankingNavPreview() {
    RankingNav(RankingScreens.total) {}
}