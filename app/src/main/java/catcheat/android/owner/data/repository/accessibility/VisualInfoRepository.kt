package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import retrofit2.Response

interface VisualInfoRepository {
    suspend fun fetchVisualInfo(): Response<VisualInfoResponse> // BaseApiResponse 대신 Response<T> 사용
}