package catcheat.android.owner.data.model.intro

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("id")
    val id: String,

    @SerializedName("password")
    val password: String
)