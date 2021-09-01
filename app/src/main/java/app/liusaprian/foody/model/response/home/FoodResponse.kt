package app.liusaprian.foody.model.response.home

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodResponse(

	@Expose
	@field:SerializedName("per_page")
	val perPage: Int,

	@Expose
	@field:SerializedName("data")
	val data: List<FoodItem>,

	@Expose
	@field:SerializedName("last_page")
	val lastPage: Int,

	@Expose
	@field:SerializedName("next_page_url")
	val nextPageUrl: String?,

	@Expose
	@field:SerializedName("prev_page_url")
	val prevPageUrl: String?,

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

@Parcelize
data class LinksItem(

	@Expose
	@field:SerializedName("active")
	val active: Boolean,

	@Expose
	@field:SerializedName("label")
	val label: String,

	@Expose
	@field:SerializedName("url")
	val url: String?
) : Parcelable

@Parcelize
data class FoodItem(

	@Expose
	@field:SerializedName("picturePath")
	val picturePath: String? = null,

	@Expose
	@field:SerializedName("types")
	val types: String? = null,

	@Expose
	@field:SerializedName("updated_at")
	val updatedAt: Long? = null,

	@Expose
	@field:SerializedName("rate")
	val rate: Int? = null,

	@Expose
	@field:SerializedName("price")
	val price: Int? = null,

	@Expose
	@field:SerializedName("name")
	val name: String? = null,

	@Expose
	@field:SerializedName("description")
	val description: String? = null,

	@Expose
	@field:SerializedName("ingredients")
	val ingredients: String? = null,

	@Expose
	@field:SerializedName("created_at")
	val createdAt: Long? = null,

	@Expose
	@field:SerializedName("id")
	val id: Int? = null,

	@Expose
	@field:SerializedName("deleted_at")
	val deletedAt: Long? = null
) : Parcelable
