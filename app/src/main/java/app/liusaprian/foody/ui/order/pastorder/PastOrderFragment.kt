package app.liusaprian.foody.ui.order.pastorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentPastOrderBinding
import app.liusaprian.foody.model.request.checkout.CheckoutRequest
import app.liusaprian.foody.ui.order.adapter.OrderAdapter

class PastOrderFragment : Fragment() {

    private val binding: FragmentPastOrderBinding by viewBinding()
    private var pastOrderList : ArrayList<CheckoutRequest>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pastOrderList = arguments?.getParcelableArrayList("transaction_list")
        if(!pastOrderList.isNullOrEmpty()) {
            val adapter = OrderAdapter(pastOrderList!!)
            val layoutManager = LinearLayoutManager(activity)
            binding.pastOrderRv.layoutManager = layoutManager
            binding.pastOrderRv.adapter = adapter
        }

    }

}