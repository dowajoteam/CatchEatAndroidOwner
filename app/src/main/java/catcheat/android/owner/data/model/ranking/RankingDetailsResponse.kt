package catcheat.android.owner.data.model.ranking

import com.google.gson.annotations.SerializedName

data class RankingDetailsResponse(
    @SerializedName("store_name")
    val storeName: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("total_ranking_score")
    val totalRankingScore: Int,

    @SerializedName("physical_score")
    val physicalScore: Int,

    @SerializedName("service_score")
    val serviceScore: Int,

    @SerializedName("visual_score")
    val visualScore: Int,

    @SerializedName("physical_accessibility")
    val physicalAccessibility: PhysicalAccessibility,

    @SerializedName("service_accessibility")
    val serviceAccessibility: ServiceAccessibility,

    @SerializedName("visual_impairment_accessibility")
    val visualImpairmentAccessibility: VisualImpairmentAccessibility

)

data class PhysicalAccessibility(
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
    val disabledToilet: Boolean,

    @SerializedName("all_criteria_met")
    val allCriteriaMet: Boolean
)

data class ServiceAccessibility(
    @SerializedName("staff_call_order_available")
    val staffCallOrderAvailable: Boolean,

    @SerializedName("all_criteria_met")
    val allCriteriaMet: Boolean
)

data class VisualImpairmentAccessibility(
    @SerializedName("guide_dog_allowed")
    val guideDogAllowed: Boolean,

    @SerializedName("self_service_available")
    val selfServiceAvailable: Boolean,

    @SerializedName("central_table_setup")
    val centralTableSetup: Boolean,

    @SerializedName("all_criteria_met")
    val allCriteriaMet: Boolean
)