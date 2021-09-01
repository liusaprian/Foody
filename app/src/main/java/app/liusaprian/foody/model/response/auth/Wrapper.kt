package app.liusaprian.foody.model.response.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wrapper<T>(

	@Expose
	@field:SerializedName("data")
	val data: T?,

	@Expose
	@field:SerializedName("meta")
	val meta: Meta?
)

