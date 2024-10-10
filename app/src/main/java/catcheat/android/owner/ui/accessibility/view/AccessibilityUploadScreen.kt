package catcheat.android.owner.ui.accessibility.view

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import catcheat.android.owner.ui.common.AccessibilityScreens
import catcheat.android.owner.ui.common.Leave
import java.io.File

@Composable
fun AccessibilityUploadScreen(navController: NavHostController, type: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        val title = when (type) {
            0 -> "물리적 접근성"
            1 -> "서비스 접근성"
            2 -> "시각장애인 지원"
            else -> ""
        }
        Leave(navController = navController, title, true)

        // currentItem 상태 정의
        var currentItem by remember { mutableStateOf(when (type) {
            0 -> AccessibilityScreens.PhysicalRequired
            1 -> AccessibilityScreens.ServiceRequired
            2 -> AccessibilityScreens.VisualRequired
            else -> AccessibilityScreens.PhysicalRequired
        }) }

        // 탭 선택 시 currentItem 업데이트
        AccessibilityUploadNav(type, currentItem) { selectedScreen ->
            currentItem = selectedScreen // 탭 선택 시 상태 업데이트
        }

        // currentItem에 따라 다른 화면 표시
        when (currentItem) {
            AccessibilityScreens.PhysicalRequired -> PhysicalRequiredItems()
            AccessibilityScreens.PhysicalOther -> PhysicalOtherItems()
            AccessibilityScreens.ServiceRequired -> ServiceRequiredItems()
            AccessibilityScreens.ServiceOther -> ServiceOtherItems()
            AccessibilityScreens.VisualRequired -> VisualRequiredItems()
            AccessibilityScreens.VisualOther -> VisualOtherItems()
            else -> {}
        }
    }
}

fun Uri.toFile(context: Context): File? {
    val inputStream = context.contentResolver.openInputStream(this) ?: return null
    val fileName = "tempImage.jpg"
    val tempFile = File(context.cacheDir, fileName)
    tempFile.outputStream().use { output ->
        inputStream.copyTo(output)
    }
    return tempFile
}