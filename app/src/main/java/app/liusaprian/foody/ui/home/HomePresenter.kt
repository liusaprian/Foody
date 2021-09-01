package app.liusaprian.foody.ui.home

import app.liusaprian.foody.network.HttpClient
import app.liusaprian.foody.ui.auth.signin.SignInContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val view: HomeContract.View
) : HomeContract.Presenter {

    private val compositeDisposable: CompositeDisposable?

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun getHomeFoodData() {
        val disposable =
            HttpClient.getInstance().getApi()!!.getFoodData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        if (it.meta?.status.equals("success", true))
                            it.data?.let { response -> view.onFetchFoodDataSuccess(response) }
                        else
                            it.meta?.message?.let { message -> view.onFetchFoodDataFailed(message) }
                    },
                    {
                        view.onFetchFoodDataFailed("Account Not Registered!")
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