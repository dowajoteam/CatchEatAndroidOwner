package catcheat.android.owner.data.repository.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import catcheat.android.owner.data.source.accessibility.PhysicalInfoUploadRemoteDataSource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class PhysicalInfoUploadRepositoryImpl @Inject constructor(
    private val remoteDataSource: PhysicalInfoUploadRemoteDataSource
) : PhysicalInfoUploadRepository {

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
        return remoteDataSource.uploadPhysicalInfo(
            doorWidthOver90cmDescription, doorWidthOver90cmImage,
            rampOrNoThresholdDescription, rampOrNoThresholdImage,
            automaticDoorOrHandleDescription, automaticDoorOrHandleImage,
            wheelchairSpaceDescription, wheelchairSpaceImage,
            wheelchairParkingSpaceDescription, wheelchairParkingSpaceImage,
            disabledToiletDescription, disabledToiletImage
        )
    }
}