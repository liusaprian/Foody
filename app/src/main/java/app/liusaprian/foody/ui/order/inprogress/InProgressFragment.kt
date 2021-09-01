package app.liusaprian.foody.ui.order.inprogress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentInProgressBinding
import app.liusaprian.foody.model.request.checkout.CheckoutRequest
import app.liusaprian.foody.ui.order.adapter.OrderAdapter

class InProgressFragment : Fragment() {

    private val binding: FragmentInProgressBinding by viewBinding()
    private var inProgressList : ArrayList<CheckoutRequest>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inProgressList = arguments?.getParcelableArrayList("transaction_list")
        if(!inProgressList.isNullOrEmpty()) {
            val adapter = OrderAdapter(inProgressList!!)
            val layoutManager = LinearLayoutManager(activity)
            binding.inProgressOrderRv.layoutManager = layoutManager
            binding.inProgressOrderRv.adapter = adapter
        }
    }
}