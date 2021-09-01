package app.liusaprian.foody.ui.order

import app.liusaprian.foody.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OrderPresenter(
    private val view: OrderContract.View
) : OrderContract.Presenter {

    private val compositeDisposable: CompositeDisposable?

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun getTransaction() {
        val disposable =
            HttpClient.getInstance().getApi()!!.getTransaction()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        if (it.meta?.status.equals("success", true))
                            it.data?.let { response -> view.onTransactionSuccess(response) }
                        else
                            it.meta?.message?.let { message -> view.onTransactionFailed(message) }
                    },
                    {
                        view.onTransactionFailed("Checkout Failed!!")
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