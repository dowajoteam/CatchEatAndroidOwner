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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import catcheat.android.owner.R
import catcheat.android.owner.ui.theme.ExtraBold25TextStyle
import catcheat.android.owner.ui.theme.Regular25TextStyle

@Composable
fun OnBoarding1Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(350.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Row {
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = painterResource(id = R.drawable.pointcircle),
                    contentDescription = "onboarding",
                    modifier = Modifier.size(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = "모두에게 닿기위한", color = Color.Black, style = ExtraBold25TextStyle)
                Text(text = " 가게의 시작", color = Color.Black, style = Regular25TextStyle)
            }
        }
        Spacer(modifier = Modifier.height(300.dp))
        Image(
            painter = painterResource(id = R.drawable.onboarding1_img),
            contentDescription = "onboarding",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Preview()
@Composable
fun OnBording1ScreenPreview() {
    OnBoarding1Screen()
}