package app.liusaprian.foody.ui.auth.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import app.liusaprian.foody.MainActivity
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentSignUpSuccessBinding
import app.liusaprian.foody.ui.auth.AuthActivity

class SignUpSuccessFragment : Fragment() {

    private val binding: FragmentSignUpSuccessBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AuthActivity).toolbar("", "", false)
        return inflater.inflate(R.layout.fragment_sign_up_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.findFoodBtn.setOnClickListener {
            startActivity(Intent (requireActivity(), MainActivity::class.java))
            activity?.finishAffinity()
        }
    }
}