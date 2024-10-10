package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PhysicalInfoService {

    @GET("/api/store/physical/accessibility/")
    suspend fun getPhysicalInfo(
        @Header("Authorization") token: String
    ): Response<PhysicalInfoResponse> // BaseApiResponse 대신 Response<T> 사용
}
