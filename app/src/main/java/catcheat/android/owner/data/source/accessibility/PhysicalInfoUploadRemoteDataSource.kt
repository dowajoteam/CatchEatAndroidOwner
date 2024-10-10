package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface PhysicalInfoUploadRemoteDataSource {
    suspend fun uploadPhysicalInfo(
        doorWidthOver90cmDescription: RequestBody?,
        doorWidthOver90cmImage: MultipartBody.Part?,
        rampOrNoThresholdDescription: RequestBody?,
        rampOrNoThresholdImage: MultipartBody.Part?,
        automaticDoorOrHandleDescription: RequestBody?,
        automaticDoorOrHandleImage: MultipartBody.Part?,
        wheelchairSpaceDescription: RequestBody?,
        wheelchairSpaceImage: MultipartBody.Part?,
        wheelchairParkingSpaceDescription: RequestBody?,
        wheelchairParkingSpaceImage: MultipartBody.Part?,
        disabledToiletDescription: RequestBody?,
        disabledToiletImage: MultipartBody.Part?
    ): Response<PhysicalInfoResponse>
}