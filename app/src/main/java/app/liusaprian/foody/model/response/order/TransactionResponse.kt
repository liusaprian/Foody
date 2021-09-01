package app.liusaprian.foody.model.response.order

import android.os.Parcelable
import app.liusaprian.foody.model.request.checkout.CheckoutRequest
import app.liusaprian.foody.model.response.home.LinksItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionResponse(

	@Expose
	@field:SerializedName("per_page")
	val perPage: Int,

	@Expose
	@field:SerializedName("data")
	val data: List<CheckoutRequest>,

	@Expose
	@field:SerializedName("last_page")
	val lastPage: Int,

	@Expose
	@field:SerializedName("next_page_url")
	val nextPageUrl: String,

	@Expose
	@field:SerializedName("prev_page_url")
	val prevPageUrl: String,

	@Expose
	@field:SerializedName("first_page_url")
	val firstPageUrl: String,

	@Expose
	@field:SerializedName("path")
	val path: String,

	@Expose
	@field:SerializedName("total")
	val total: Int,

	@Expose
	@field:SerializedName("last_page_url")
	val lastPageUrl: String,

	@Expose
	@field:SerializedName("from")
	val from: Int,

	@Expose
	@field:SerializedName("links")
	val links: List<LinksItem>,

	@Expose
	@field:SerializedName("to")
	val to: Int,

	@Expose
	@field:SerializedName("current_page")
	val currentPage: Int
) : Parcelable