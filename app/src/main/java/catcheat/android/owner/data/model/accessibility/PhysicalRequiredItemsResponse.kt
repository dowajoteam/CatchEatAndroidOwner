package catcheat.android.owner.data.model.accessibility

import com.google.gson.annotations.SerializedName

data class PhysicalRequiredItemsResponse(
    @SerializedName("door_width_over_90cm")
    val doorWidthOver90cm: Boolean,

    @SerializedName("ramp_or_no_threshold")
    val rampOrNoThreshold: Boolean,

    @SerializedName("automatic_door_or_handle")
    val automaticDoorOrHandle: Boolean,

    @SerializedName("wheelchair_space")
    val wheelchairSpace: Boolean,

    @SerializedName("wheelchair_parking_space")
    val wheelchairParkingSpace: Boolean,

    @SerializedName("disabled_toilet")
    val disabledToilet: Boolean
)