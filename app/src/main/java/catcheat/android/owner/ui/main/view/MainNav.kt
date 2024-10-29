package catcheat.android.owner.ui.main.view

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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import catcheat.android.owner.ui.common.MainScreens
import catcheat.android.owner.ui.theme.Bold30TextStyle

@Composable
fun MainNav(
    currentScreen: MainScreens,
    onTabSelected: (MainScreens) -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            MainScreens.entries.forEach { screen ->
                val isSelected = currentScreen == screen

                val color by animateColorAsState(
                    targetValue = if (isSelected) Color.Black else Color.Gray,
                    animationSpec = spring(), label = ""
                )

                Text(
                    text = when (screen.displayName) {
                        "Home" -> "캐칫 홈"
                        "Ranking" -> "캐칫 랭킹"
                        else -> ""
                    },
                    style = Bold30TextStyle,
                    color = color,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .semantics {
                            contentDescription = when (screen.displayName) {
                                "Home" -> "캐칫 홈, 홈 화면으로 가기"
                                "Ranking" -> "캐칫 랭킹, 랭킹 화면으로 가기"
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
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Preview()
@Composable
fun MainNavPreview() {
    MainNav(MainScreens.Home) {}
}