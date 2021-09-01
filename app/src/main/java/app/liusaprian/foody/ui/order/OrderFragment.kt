package app.liusaprian.foody.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentOrderBinding
import app.liusaprian.foody.model.request.checkout.CheckoutRequest
import app.liusaprian.foody.model.response.order.TransactionResponse
import app.liusaprian.foody.ui.order.adapter.OrderSectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OrderFragment : Fragment(), OrderContract.View {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: OrderPresenter
    private var inProgressList : ArrayList<CheckoutRequest> = ArrayList()
    private var pastOrderList : ArrayList<CheckoutRequest> = ArrayList()

    companion object {
        val TAB_TITLES = intArrayOf(R.string.in_progress, R.string.past_order)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOrderBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = OrderPresenter(this)
        presenter.getTransaction()

        binding.orderHeader.toolbar.title = "Your Orders"
        binding.orderHeader.toolbar.subtitle = "See your orders here"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if(transactionResponse.data.isNullOrEmpty()) emptyLayout(true)
        else {
            emptyLayout(false)
            transactionResponse.data.map {
                if(it.status.equals("on_delivery", true) ||
                    it.status.equals("pending", true) ) inProgressList.add(it)
                else pastOrderList.add(it)
            }

            val sectionsPagerAdapter = OrderSectionsPagerAdapter(requireActivity() as AppCompatActivity)

            with(binding) {
                viewPagerOrder.adapter = sectionsPagerAdapter
                TabLayoutMediator(orderTabs, viewPagerOrder) { tab, position ->
                    tab.text = resources.getString(TAB_TITLES[position])
                }.attach()
            }

            sectionsPagerAdapter.setData(inProgressList, pastOrderList)
        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun emptyLayout(show: Boolean) {
        binding.emptyOrderLayout.visibility = if(show) View.VISIBLE else View.GONE
        binding.orderLayout.visibility = if(show) View.GONE else View.VISIBLE
        binding.orderHeader.toolbar.visibility = if(show) View.GONE else View.VISIBLE
    }
}