package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import catcheat.android.owner.data.service.accessibility.VisualInfoService
import retrofit2.Response
import javax.inject.Inject

class VisualInfoRemoteDataSourceImpl @Inject constructor(
    private val visualInfoService: VisualInfoService
) : VisualInfoRemoteDataSource {

    override suspend fun fetchVisualInfo(): Response<VisualInfoResponse> {
        val token = "Token <your-token>"
        return visualInfoService.getVisualInfo(token)
    }
}