package app.liusaprian.foody.model.request.checkout

import android.os.Parcelable
import app.liusaprian.foody.model.response.auth.User
import app.liusaprian.foody.model.response.home.FoodItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckoutRequest(

	@Expose
	@field:SerializedName("total")
	val total: Int,

	@Expose
	@field:SerializedName("payment_url")
	val paymentUrl: String,

	@Expose
	@field:SerializedName("quantity")
	val quantity: Int,

	@Expose
	@field:SerializedName("updated_at")
	val updatedAt: String,

	@Expose
	@field:SerializedName("user_id")
	val userId: Int,

	@Expose
	@field:SerializedName("created_at")
	val createdAt: String,

	@Expose
	@field:SerializedName("id")
	val id: Int,

	@Expose
	@field:SerializedName("food_id")
	val foodId: Int,

	@Expose
	@field:SerializedName("deleted_at")
	val deletedAt: String,

	@Expose
	@field:SerializedName("user")
	val user: User,

	@Expose
	@field:SerializedName("food")
	val food: FoodItem,

	@Expose
	@field:SerializedName("status")
	val status: String
) : Parcelable