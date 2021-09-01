package app.liusaprian.foody.model.request.auth

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterRequest(

    @Expose
    @field:SerializedName("name")
    var name: String,

    @Expose
    @field:SerializedName("email")
    var email: String,

    @Expose
    @field:SerializedName("password")
    var password: String,

    @Expose
    @field:SerializedName("password_confirmation")
    var confirmPassword: String,

    @Expose
    @field:SerializedName("address")
    var address: String,

    @Expose
    @field:SerializedName("city")
    var city: String,

    @Expose
    @field:SerializedName("houseNumber")
    var houseNumber: Int,

    @Expose
    @field:SerializedName("phoneNumber")
    var phoneNumber: String,

    @Expose
    @field:SerializedName("file")
    var filePath: Uri? = null

) : Parcelable
