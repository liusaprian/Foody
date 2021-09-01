package app.liusaprian.foody.network

import app.liusaprian.foody.model.response.auth.AuthResponse
import app.liusaprian.foody.model.response.auth.Wrapper
import app.liusaprian.foody.model.request.checkout.CheckoutRequest
import app.liusaprian.foody.model.response.home.FoodResponse
import app.liusaprian.foody.model.response.order.TransactionResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Observable<Wrapper<AuthResponse>>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") confirmPassword: String,
        @Field("address") address: String,
        @Field("city") city: String,
        @Field("houseNumber") houseNumber: Int,
        @Field("phoneNumber") phoneNumber: String,
    ) : Observable<Wrapper<AuthResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(
        @Part image: MultipartBody.Part,
    ) : Observable<Wrapper<Any>>

    @GET("food")
    fun getFoodData() : Observable<Wrapper<FoodResponse>>

    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("food_id") foodId: Int,
        @Field("user_id") userId: Int,
        @Field("quantity") quantity: Int,
        @Field("total") total: Int,
        @Field("status") status: String
    ) : Observable<Wrapper<CheckoutRequest>>

    @GET("transaction")
    fun getTransaction() : Observable<Wrapper<TransactionResponse>>
}