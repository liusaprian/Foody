package app.liusaprian.foody.ui.detail.payment

import android.view.View
import app.liusaprian.foody.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentPresenter(
    private val view: PaymentContract.View
) : PaymentContract.Presenter {

    private val compositeDisposable: CompositeDisposable?

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun submitCheckout(
        foodId: Int,
        userId: Int,
        quantity: Int,
        total: Int,
        viewParams: View
    ) {
        view.showLoading()
        val disposable =
            HttpClient.getInstance().getApi()!!.checkout(foodId, userId, quantity, total, "PENDING")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        view.dismissLoading()
                        if (it.meta?.status.equals("success", true))
                            it.data?.let { response -> view.onCheckoutSuccess(response, viewParams) }
                        else
                            it.meta?.message?.let { message -> view.onCheckoutFailed(message) }
                    },
                    {
                        view.dismissLoading()
                        view.onCheckoutFailed("Checkout Failed!!")
                    }
                )
        compositeDisposable?.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        compositeDisposable?.clear()
    }

}