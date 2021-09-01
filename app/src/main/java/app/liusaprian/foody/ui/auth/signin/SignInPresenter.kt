package app.liusaprian.foody.ui.auth.signin

import app.liusaprian.foody.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignInPresenter(
    private val view: SignInContract.View
) : SignInContract.Presenter {

    private val compositeDisposable: CompositeDisposable?

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun submitLogin(email: String, password: String) {
        view.showLoading()
        val disposable =
            HttpClient.getInstance().getApi()!!.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        view.dismissLoading()
                        if (it.meta?.status.equals("success", true))
                            it.data?.let { loginResponse -> view.onLoginSuccess(loginResponse) }
                        else
                            it.meta?.message?.let { message -> view.onLoginFailed(message) }
                    },
                    {
                        view.dismissLoading()
                        view.onLoginFailed("Account Not Registered!")
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