package catcheat.android.owner.data.model.intro

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("id")
    val id: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("store_name")
    val storeName: String,

    @SerializedName("business_type")
    val businessType: String,

    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("address")
    val address: String
)