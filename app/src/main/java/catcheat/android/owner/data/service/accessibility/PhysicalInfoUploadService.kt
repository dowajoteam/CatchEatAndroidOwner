package catcheat.android.owner.data.service.accessibility

import catcheat.android.owner.data.accessibility.PhysicalInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part

interface PhysicalInfoUploadService {

    @Multipart
    @PATCH("/api/store/physical/accessibility/")
    suspend fun uploadPhysicalInfo(
        @Header("Authorization") token: String,
        @Part("door_width_over_90cm_description") doorWidthOver90cmDescription: RequestBody?,
        @Part doorWidthOver90cmImage: MultipartBody.Part?,
        @Part("ramp_or_no_threshold_description") rampOrNoThresholdDescription: RequestBody?,
        @Part rampOrNoThresholdImage: MultipartBody.Part?,
        @Part("automatic_door_or_handle_description") automaticDoorOrHandleDescription: RequestBody?,
        @Part automaticDoorOrHandleImage: MultipartBody.Part?,
        @Part("wheelchair_space_description") wheelchairSpaceDescription: RequestBody?,
        @Part wheelchairSpaceImage: MultipartBody.Part?,
        @Part("wheelchair_parking_space_description") wheelchairParkingSpaceDescription: RequestBody?,
        @Part wheelchairParkingSpaceImage: MultipartBody.Part?,
        @Part("disabled_toilet_description") disabledToiletDescription: RequestBody?,
        @Part disabledToiletImage: MultipartBody.Part?
    ): Response<PhysicalInfoResponse>
}