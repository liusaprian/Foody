package app.liusaprian.foody.ui.detail.payment

import app.liusaprian.foody.base.BasePresenter
import app.liusaprian.foody.base.BaseView
import app.liusaprian.foody.model.request.checkout.CheckoutRequest

interface PaymentContract {

    interface View: BaseView {
        fun onCheckoutSuccess(checkoutRequest: CheckoutRequest, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter: PaymentContract, BasePresenter {
        fun submitCheckout(foodId: Int, userId: Int, quantity: Int, total: Int, view: android.view.View)
    }
}