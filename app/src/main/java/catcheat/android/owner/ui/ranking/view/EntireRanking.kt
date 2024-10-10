package catcheat.android.owner.ui.ranking.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import catcheat.android.owner.ui.common.RankingList
import catcheat.android.owner.ui.ranking.viewmodel.EntireRankingViewModel

@Composable
fun EntireRanking(navController: NavHostController, viewModel: EntireRankingViewModel = hiltViewModel()) {
    val rankingList by viewModel.rankingList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Spacer(modifier = Modifier.height(15.dp))

    // 에러 메시지가 있으면 UI에서 표시할 수 있음
    errorMessage?.let {
        // 에러 메시지 출력하는 컴포넌트 (예: Text)
    }

    LazyColumn {
        items(rankingList) { ranking ->
            RankingList(
                rank = ranking.rank,
                storeId = ranking.storeId,
                imageUrl = ranking.image,
                storeName = ranking.storeName,
                physicalAccessibility = ranking.physicalAccessibility,
                serviceAccessibility = ranking.serviceAccessibility,
                visualImpairmentAccessibility = ranking.visualImpairmentAccessibility,
                navController = navController
            )
        }
    }
}