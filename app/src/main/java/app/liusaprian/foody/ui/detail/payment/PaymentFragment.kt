package app.liusaprian.foody.ui.detail.payment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.foody.Foody
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentPaymentBinding
import app.liusaprian.foody.model.Order
import app.liusaprian.foody.model.response.auth.User
import app.liusaprian.foody.model.request.checkout.CheckoutRequest
import app.liusaprian.foody.model.response.home.FoodItem
import app.liusaprian.foody.ui.detail.DetailActivity
import app.liusaprian.foody.ui.detail.payment.adapter.PaymentOrderAdapter
import com.google.gson.Gson

class PaymentFragment : Fragment(), PaymentContract.View {

    private val binding : FragmentPaymentBinding by viewBinding()
    private var foodOrder: ArrayList<Order> = ArrayList()
    private lateinit var presenter: PaymentPresenter
    private lateinit var progressDialog: Dialog
    private lateinit var user: User
    private var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DetailActivity).toolbar("Payment", "One step away to eat", true)

        val data = arguments?.getParcelable<FoodItem>("food")
        initView(data)
        initLoadingView()

        presenter = PaymentPresenter(this)

        with(binding) {
            paymentOrderRv.adapter = PaymentOrderAdapter(foodOrder)
            paymentOrderRv.layoutManager = LinearLayoutManager(context)

            checkoutBtn.setOnClickListener {
                data?.let { food ->
                    presenter.submitCheckout(
                        food.id!!,
                        user.id,
                        14,
                        total,
                        it
                    )
                }
            }
        }
    }

    private fun initView(food: FoodItem?) {
        food?.let {
            foodOrder.add(
                Order(
                    food,
                    14
                )
            )
            val subtotal: Int = food.price?.times(14) ?: 0
            val driverFee: Int = food.price?.div(20) ?: 0
            val taxFee: Int = food.price?.div(10) ?: 0
            total = subtotal + driverFee + taxFee

            user = Gson().fromJson(Foody.getApp().getUser(), User::class.java)

            with(binding) {
                paymentSubtotalPrice.text = getString(R.string.food_price, "%,d".format(subtotal).replace(',', '.'))
                paymentDriverFee.text = getString(R.string.food_price, "%,d".format(driverFee).replace(',', '.'))
                paymentTax.text = getString(R.string.food_price, "%,d".format(taxFee).replace(',', '.'))
                paymentTotalPrice.text = getString(R.string.food_price, "%,d".format(total).replace(',', '.'))

                paymentName.text = user.name
                paymentPhone.text = user.phoneNumber
                paymentAddress.text = user.address
                paymentHouse.text = user.houseNumber
                paymentCity.text = user.city
            }
        }

    }

    override fun onCheckoutSuccess(checkoutRequest: CheckoutRequest, view: View) {
        val midtransIntent = Intent(Intent.ACTION_VIEW)
        midtransIntent.data = Uri.parse(checkoutRequest.paymentUrl)
        startActivity(midtransIntent)
        findNavController().navigate(R.id.to_payment_success)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("InflateParams")
    private fun initLoadingView() {
        progressDialog = Dialog(requireActivity())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog.show()
    }

    override fun dismissLoading() {
        progressDialog.dismiss()
    }

}