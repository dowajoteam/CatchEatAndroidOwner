package catcheat.android.owner.data.accessibility

import com.google.gson.annotations.SerializedName

data class PhysicalInfoResponse(
    @SerializedName("door_width_over_90cm_description")
    val doorWidthOver90cmDescription: String,

    @SerializedName("door_width_over_90cm_image")
    val doorWidthOver90cmImage: String?,

    @SerializedName("ramp_or_no_threshold_description")
    val rampOrNoThresholdDescription: String,

    @SerializedName("ramp_or_no_threshold_image")
    val rampOrNoThresholdImage: String?,

    @SerializedName("automatic_door_or_handle_description")
    val automaticDoorOrHandleDescription: String,

    @SerializedName("automatic_door_or_handle_image")
    val automaticDoorOrHandleImage: String?,

    @SerializedName("wheelchair_space_description")
    val wheelchairSpaceDescription: String,

    @SerializedName("wheelchair_space_image")
    val wheelchairSpaceImage: String?,

    @SerializedName("wheelchair_parking_space_description")
    val wheelchairParkingSpaceDescription: String,

    @SerializedName("wheelchair_parking_space_image")
    val wheelchairParkingSpaceImage: String?,

    @SerializedName("disabled_toilet_description")
    val disabledToiletDescription: String,

    @SerializedName("disabled_toilet_image")
    val disabledToiletImage: String?
)
