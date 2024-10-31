package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.model.accessibility.ServiceRequiredItemsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ServiceRequiredItemsService {

    @GET("/api/store/service/requirements-status/")
    suspend fun getServiceRequiredItems(
        @Header("Authorization") token: String
    ): Response<ServiceRequiredItemsResponse>
}