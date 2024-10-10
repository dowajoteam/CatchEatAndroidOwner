package catcheat.android.owner.data.model.main

import com.google.gson.annotations.SerializedName

data class StoreInfoResponse(
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

    @SerializedName("overall_rank")
    val overallRank: String,

    @SerializedName("physical_accessibility")
    val physicalAccessibility: Boolean,

    @SerializedName("service_accessibility")
    val serviceAccessibility: Boolean,

    @SerializedName("visual_impairment_accessibility")
    val visualImpairmentAccessibility: Boolean
)