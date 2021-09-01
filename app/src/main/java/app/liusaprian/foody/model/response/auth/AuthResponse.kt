package app.liusaprian.foody.model.response.auth

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthResponse(

    @Expose
    @field:SerializedName("access_token")
    val accessToken: String,

    @Expose
    @field:SerializedName("token_type")
    val tokenType: String,

    @Expose
    @field:SerializedName("user")
    val user: User
) : Parcelable
