package catcheat.android.owner.ui.intro.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import catcheat.android.owner.R
import catcheat.android.owner.ui.theme.Bold20TextStyle
import catcheat.android.owner.ui.theme.Bold30TextStyle
import catcheat.android.owner.ui.theme.CatchEat

@Composable
fun SignUpCompletedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.completionimage),
                contentDescription = "입점신청 완료 이미지",
                modifier = Modifier.size(300.dp)
            )

            Text(text = "입점 신청 완료!", style = Bold20TextStyle, color = Color.Gray)

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "캐칫 가게 승인 절차 완료 후,", style = Bold30TextStyle, color = Color.Black)
            Text(text = "등록해주신 번호로,", style = Bold30TextStyle, color = Color.Black)
            Row {
                Text(text = "입점 확인 안내 문자", style = Bold30TextStyle, color = CatchEat)
                Text(text = "가", style = Bold30TextStyle, color = Color.Black)
            }
            Text(text = "전송될 예정입니다.", style = Bold30TextStyle, color = Color.Black)
        }
    }
}

@Preview()
@Composable
fun SignUpCompletedScreenPreview() {
    SignUpCompletedScreen()
}