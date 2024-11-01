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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
            .background(Color.White)
            .padding(20.dp)
            .semantics { contentDescription = "입점 신청 완료! 캐칫 가게 승인 절차 완료 후, 등록해주신 번호로 입점 확인 안내 문자가 전송될 예정입니다." },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.completionimage),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Text(text = "입점 신청 완료!", style = Bold20TextStyle, color = Color.Gray, modifier = Modifier.clearAndSetSemantics {})
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "캐칫 가게 승인 절차 완료 후,", style = Bold30TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
        Text(text = "등록해주신 번호로,", style = Bold30TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
        Row {
            Text(text = "입점 확인 안내 문자", style = Bold30TextStyle, color = CatchEat, modifier = Modifier.clearAndSetSemantics {})
            Text(text = "가", style = Bold30TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
        }
        Text(text = "전송될 예정입니다.", style = Bold30TextStyle, color = Color.Black, modifier = Modifier.clearAndSetSemantics {})
    }
}

@Preview()
@Composable
fun SignUpCompletedScreenPreview() {
    SignUpCompletedScreen()
}