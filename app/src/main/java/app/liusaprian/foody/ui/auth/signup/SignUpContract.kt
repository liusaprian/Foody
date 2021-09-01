package app.liusaprian.foody.ui.auth.signup

import android.net.Uri
import app.liusaprian.foody.base.BasePresenter
import app.liusaprian.foody.base.BaseView
import app.liusaprian.foody.model.request.auth.RegisterRequest
import app.liusaprian.foody.model.response.auth.AuthResponse

interface SignUpContract {

    interface View: BaseView {
        fun onRegisterSuccess(authResponse: AuthResponse, view: android.view.View)
        fun onRegisterPhotoSuccess(view: android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter: SignUpContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view: android.view.View)
        fun submitPhoto(filepath: Uri, view: android.view.View)
    }
}