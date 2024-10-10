package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import retrofit2.Response

interface PhysicalInfoRepository {
    suspend fun fetchPhysicalInfo(): Response<PhysicalInfoResponse>
}