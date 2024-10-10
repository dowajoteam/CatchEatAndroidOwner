package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import retrofit2.Response

interface PhysicalInfoRemoteDataSource {
    suspend fun fetchPhysicalInfo(): Response<PhysicalInfoResponse>
}