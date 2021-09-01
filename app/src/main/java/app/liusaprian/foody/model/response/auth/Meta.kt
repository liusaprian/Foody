package app.liusaprian.foody.model.response.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meta(

    @Expose
    @field:SerializedName("code")
    val code: Int,

    @Expose
    @field:SerializedName("message")
    val message: String,

    @Expose
    @field:SerializedName("status")
    val status: String
)