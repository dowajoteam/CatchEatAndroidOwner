package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ServiceInfoService {

    @GET("/api/store/service/accessibility/")
    suspend fun getServiceInfo(
        @Header("Authorization") token: String
    ): Response<ServiceInfoResponse> // BaseApiResponse 대신 Response<T> 사용
}