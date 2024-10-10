package catcheat.android.owner.data.model.accessibility

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class PhysicalInfoUploadRequest(
    val doorWidthOver90cmDescription: RequestBody? = null,
    val doorWidthOver90cmImage: MultipartBody.Part? = null,

    val rampOrNoThresholdDescription: RequestBody? = null,
    val rampOrNoThresholdImage: MultipartBody.Part? = null,

    val automaticDoorOrHandleDescription: RequestBody? = null,
    val automaticDoorOrHandleImage: MultipartBody.Part? = null,

    val wheelchairSpaceDescription: RequestBody? = null,
    val wheelchairSpaceImage: MultipartBody.Part? = null,

    val wheelchairParkingSpaceDescription: RequestBody? = null,
    val wheelchairParkingSpaceImage: MultipartBody.Part? = null,

    val disabledToiletDescription: RequestBody? = null,
    val disabledToiletImage: MultipartBody.Part? = null
)
