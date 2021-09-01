package app.liusaprian.foody.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import app.liusaprian.foody.Foody
import app.liusaprian.foody.MainActivity
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentSignInBinding
import app.liusaprian.foody.model.response.auth.AuthResponse
import app.liusaprian.foody.ui.auth.AuthActivity
import com.google.gson.Gson

class SignInFragment : Fragment(), SignInContract.View {

    private val binding: FragmentSignInBinding by viewBinding()
    private lateinit var presenter: SignInPresenter
    private lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SignInPresenter(this)
        initLoadingView()

        if(!Foody.getApp().getToken().isNullOrEmpty()) goToMain()

        binding.createNewAccountBtn.setOnClickListener {
            findNavController()
                .navigate(
                    R.id.to_sign_up,
                    null,
                )
        }

        binding.signInBtn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passEt.text.toString()
            when {
                email.isEmpty() -> {
                    binding.emailEt.error = "Email cannot be empty!"
                    binding.emailEt.requestFocus()
                }
                password.isEmpty() -> {
                    binding.passEt.error = "Password cannot be empty!"
                    binding.passEt.requestFocus()
                }
                else -> presenter.submitLogin(email, password)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as AuthActivity).toolbar(getString(R.string.sign_in), getString(R.string.sign_in_subtitle), visible = true, visibleLeadingIcon = false)
    }

    override fun onLoginSuccess(authResponse: AuthResponse) {
        Foody.getApp().setToken(authResponse.accessToken)
        Foody.getApp().setUser(Gson().toJson(authResponse.user))

        goToMain()
    }

    private fun goToMain() {
        val toMain = Intent(requireActivity(), MainActivity::class.java)
        startActivity(toMain)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

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