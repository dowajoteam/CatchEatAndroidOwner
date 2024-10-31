package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.model.accessibility.PhysicalRequiredItemsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PhysicalRequiredItemsService {

    @GET("/api/store/physical/requirements-status/")
    suspend fun getPhysicalRequiredItems(
        @Header("Authorization") token: String
    ): Response<PhysicalRequiredItemsResponse>
}