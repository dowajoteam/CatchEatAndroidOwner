package catcheat.android.owner.ui.ranking.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import catcheat.android.owner.ui.common.RankingList
import catcheat.android.owner.ui.ranking.viewmodel.ServiceRankingViewModel

@Composable
fun ServiceRanking(navController: NavHostController, viewModel: ServiceRankingViewModel = hiltViewModel()) {
    val rankingList by viewModel.rankingList.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LazyColumn (
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, bottom = 30.dp)
            .semantics { contentDescription = "서비스 접근성 랭킹입니다." }
    ) {
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