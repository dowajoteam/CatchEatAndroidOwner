package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import catcheat.android.owner.data.service.accessibility.PhysicalInfoService
import retrofit2.Response
import javax.inject.Inject

class PhysicalInfoRemoteDataSourceImpl @Inject constructor(
    private val physicalInfoService: PhysicalInfoService
) : PhysicalInfoRemoteDataSource {

    override suspend fun fetchPhysicalInfo(): Response<PhysicalInfoResponse> {
        // Authorization 헤더에 토큰을 전달
        val token = "Token <your-token>"
        return physicalInfoService.getPhysicalInfo(token)
    }
}