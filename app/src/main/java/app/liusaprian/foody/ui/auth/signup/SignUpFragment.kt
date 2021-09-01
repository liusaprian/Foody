package app.liusaprian.foody.ui.auth.signup

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentSignUpBinding
import app.liusaprian.foody.model.request.auth.RegisterRequest
import app.liusaprian.foody.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker

class SignUpFragment : Fragment() {

    private val binding: FragmentSignUpBinding by viewBinding()
    var filePath: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AuthActivity).toolbar(getString(R.string.sign_up), getString(R.string.sign_up_subtitle), true)
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.signUpProfilePictureIv.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .createIntent {
                    resultLauncher.launch(it)
                }
        }
        with(binding) {
            continueBtn.setOnClickListener {
                val name = nameEt.text.toString()
                val email = emailEt.text.toString()
                val password = passEt.text.toString()

                when {
                    name.isEmpty() -> {
                        nameEt.error = "Name must be filled!"
                        nameEt.requestFocus()
                    }
                    email.isEmpty() -> {
                        emailEt.error = "Email must be filled!"
                        emailEt.requestFocus()
                    }
                    password.isEmpty() -> {
                        passEt.error = "Password must be filled!"
                        passEt.requestFocus()
                    }
                    else -> {
                        val userData = RegisterRequest(
                            name,
                            email,
                            password,
                            password,
                            "",
                            "",
                            0,
                            "",
                            filePath
                        )
                        val bundle = Bundle()
                        bundle.putParcelable("user", userData)

                        findNavController().navigate(
                            R.id.to_sign_up_address,
                            bundle
                        )
                    }
                }
            }
        }
    }

    private fun initDummy() {
        with(binding) {
            nameEt.setText("Lius Aprian")
            emailEt.setText("aprianlius@gmail.com")
            passEt.setText("123123123")
        }
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    filePath = it.data?.data

                    Glide.with(this)
                        .load(filePath)
                        .into(binding.signUpProfilePictureIv)
                }
                ImagePicker.RESULT_ERROR -> Toast.makeText(context, ImagePicker.getError(it.data), Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
}