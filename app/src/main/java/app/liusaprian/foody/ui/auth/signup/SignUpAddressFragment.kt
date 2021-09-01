package app.liusaprian.foody.ui.auth.signup

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import app.liusaprian.foody.Foody
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentSignUpAddressBinding
import app.liusaprian.foody.model.request.auth.RegisterRequest
import app.liusaprian.foody.model.response.auth.AuthResponse
import app.liusaprian.foody.ui.auth.AuthActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class SignUpAddressFragment : Fragment(), SignUpContract.View {

    private val binding: FragmentSignUpAddressBinding by viewBinding()
    private lateinit var userData: RegisterRequest
    private lateinit var presenter: SignUpPresenter
    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AuthActivity).toolbar("Address", "Make sure it is valid", true)
        return inflater.inflate(R.layout.fragment_sign_up_address, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SignUpPresenter(this)
        userData = arguments?.getParcelable("user")!!

        initLoadingView()
        initListener()
    }

    private fun initListener() {
        binding.signUpBtn.setOnClickListener {
            with(binding) {
                val phone = phoneEt.text.toString()
                val address = addressEt.text.toString()
                val houseNumber = houseEt.text.toString()
                val city = cityEt.text.toString()

                when {
                    phone.isEmpty() -> {
                        phoneEt.error = "Phone number must be filled!"
                        phoneEt.requestFocus()
                    }
                    address.isEmpty() -> {
                        addressEt.error = "Address must be filled!"
                        addressEt.requestFocus()
                    }
                    houseNumber.isEmpty() -> {
                        houseEt.error = "House number must be filled!"
                        houseEt.requestFocus()
                    }
                    city.isEmpty() -> {
                        cityEt.error = "City must be filled!"
                        cityEt.requestFocus()
                    }
                    else -> {
                        userData.let {
                            it.address = address
                            it.city = city
                            it.phoneNumber = phone
                            it.houseNumber = houseNumber.toInt()
                        }
                        presenter.submitRegister(userData, it)
                    }
                }
            }
        }

    }

    override fun onRegisterSuccess(authResponse: AuthResponse, view: View) {
        Foody.getApp().setToken(authResponse.accessToken)
        Foody.getApp().setUser(Gson().toJson(authResponse.user))

        if(userData.filePath == null) successSignUp()
        else presenter.submitPhoto(userData.filePath!!, view)
    }

    override fun onRegisterPhotoSuccess(view: View) {
        successSignUp()
    }

    private fun successSignUp() {
        findNavController().navigate(
            R.id.to_sign_up_success,
            null
        )
    }

    override fun onRegisterFailed(message: String) {
        val snackbar = Snackbar.make(requireView(), "Email is already registered!", Snackbar.LENGTH_LONG)
        val layoutParams = LinearLayout.LayoutParams(snackbar.view.layoutParams)
        layoutParams.gravity = Gravity.TOP
        snackbar.view.layoutParams = layoutParams
        snackbar.setBackgroundTint(ContextCompat.getColor(requireActivity(), R.color.red))
        snackbar.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
        snackbar.show()
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