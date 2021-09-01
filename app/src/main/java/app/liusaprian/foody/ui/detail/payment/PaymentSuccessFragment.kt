package app.liusaprian.foody.ui.detail.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentPaymentSuccessBinding
import app.liusaprian.foody.ui.detail.DetailActivity

class PaymentSuccessFragment : Fragment() {

    private val binding: FragmentPaymentSuccessBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as DetailActivity).toolbar("", "", false)

        binding.orderOtherFoodBtn.setOnClickListener {
            requireActivity().finish()
        }

        binding.viewOrderBtn.setOnClickListener {
            requireActivity().finish()
        }
    }
}