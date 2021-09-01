package app.liusaprian.foody.ui.auth.signin

import app.liusaprian.foody.base.BasePresenter
import app.liusaprian.foody.base.BaseView
import app.liusaprian.foody.model.response.auth.AuthResponse

interface SignInContract {

    interface View: BaseView {
        fun onLoginSuccess(authResponse: AuthResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter: SignInContract, BasePresenter {
        fun submitLogin(email: String, password: String)
    }
}