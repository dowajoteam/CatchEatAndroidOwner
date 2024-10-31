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
import androidx.compose.ui.semantics.clearAndSetSemantics
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
            .background(Color.White)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Spacer(modifier = Modifier.height(250.dp))
            Row {
                Spacer(modifier = Modifier.width(15.dp))
                Image(
                    painter = painterResource(id = R.drawable.pointcircle),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = "모두에게 닿기위한", color = Color.Black, style = ExtraBold25TextStyle, modifier = Modifier.clearAndSetSemantics {})
                Text(text = " 가게의 시작", color = Color.Black, style = Regular25TextStyle, modifier = Modifier.clearAndSetSemantics {})
            }
        }
        Column {
            Image(
                painter = painterResource(id = R.drawable.onboarding1_img),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview
@Composable
fun OnBoarding1ScreenPreview() {
    OnBoarding1Screen()
}