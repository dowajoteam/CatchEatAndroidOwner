package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.model.accessibility.VisualInfoResponse
import retrofit2.Response

interface VisualInfoRemoteDataSource {
    suspend fun fetchVisualInfo(): Response<VisualInfoResponse>
}