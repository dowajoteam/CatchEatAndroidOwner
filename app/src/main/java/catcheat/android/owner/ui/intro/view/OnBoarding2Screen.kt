package catcheat.android.owner.ui.intro.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import catcheat.android.owner.R
import catcheat.android.owner.ui.theme.Bold25TextStyle
import catcheat.android.owner.ui.theme.CatchEat
import catcheat.android.owner.ui.theme.Regular15TextStyle

@Composable
fun OnBoarding2Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Text(text = "모두에게 닿기위한", style = Bold25TextStyle, color = CatchEat, modifier = Modifier.clearAndSetSemantics {})
            Text(text = " 가게의 시작", style = Bold25TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "접근성 점수 높이고 무료 홍보 받자!", style = Regular15TextStyle, color = Color.Gray, modifier = Modifier.clearAndSetSemantics {})
        Image(
            painter = painterResource(R.drawable.onboarding2_img),
            contentDescription = "null",
            modifier = Modifier.size(300.dp)
        )
    }
}

@Preview
@Composable
fun OnBoarding2ScreenPreview() {
    OnBoarding2Screen()
}