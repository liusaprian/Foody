package app.liusaprian.foody.ui.auth.signup

import android.net.Uri
import android.view.View
import app.liusaprian.foody.model.request.auth.RegisterRequest
import app.liusaprian.foody.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignUpPresenter(
    private val view: SignUpContract.View
) : SignUpContract.Presenter {

    private val compositeDisposable: CompositeDisposable?

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(registerRequest: RegisterRequest, viewParams: View) {
        view.showLoading()
        val disposable =
            HttpClient.getInstance().getApi()!!
                .register(
                    registerRequest.name,
                    registerRequest.email,
                    registerRequest.password,
                    registerRequest.confirmPassword,
                    registerRequest.address,
                    registerRequest.city,
                    registerRequest.houseNumber,
                    registerRequest.phoneNumber,
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        view.dismissLoading()
                        if (it.meta?.status.equals("success", true))
                            it.data?.let { response -> view.onRegisterSuccess(response, viewParams) }
                        else
                            it.meta?.message?.let { message -> view.onRegisterFailed(message) }
                    },
                    {
                        view.dismissLoading()
                        view.onRegisterFailed("Register Failed!")
                    }
                )
        compositeDisposable?.add(disposable)
    }

    override fun submitPhoto(filepath: Uri, viewParams: View) {
        view.showLoading()

        val profileImageFile = File(filepath.path!!)
        val profileImageRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), profileImageFile)
        val profileImageParam = MultipartBody.Part.createFormData("file", profileImageFile.name, profileImageRequestBody)

        val disposable =
            HttpClient.getInstance().getApi()!!.registerPhoto(profileImageParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        view.dismissLoading()
                        if (it.meta?.status.equals("success", true))
                            it.data?.let { view.onRegisterPhotoSuccess(viewParams) }
                        else
                            it.meta?.message?.let { message -> view.onRegisterFailed(message) }
                    },
                    {
                        view.dismissLoading()
                        view.onRegisterFailed(it.message.toString())
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