package catcheat.android.owner.data.source.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import catcheat.android.owner.data.service.accessibility.PhysicalInfoUploadService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class PhysicalInfoUploadRemoteDataSourceImpl @Inject constructor(
    private val physicalInfoUploadService: PhysicalInfoUploadService
) : PhysicalInfoUploadRemoteDataSource {

    override suspend fun uploadPhysicalInfo(
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
    ): Response<PhysicalInfoResponse> {
        val token = "Token <your-token>"
        return physicalInfoUploadService.uploadPhysicalInfo(
            token,
            doorWidthOver90cmDescription, doorWidthOver90cmImage,
            rampOrNoThresholdDescription, rampOrNoThresholdImage,
            automaticDoorOrHandleDescription, automaticDoorOrHandleImage,
            wheelchairSpaceDescription, wheelchairSpaceImage,
            wheelchairParkingSpaceDescription, wheelchairParkingSpaceImage,
            disabledToiletDescription, disabledToiletImage
        )
    }
}