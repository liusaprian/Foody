package app.liusaprian.foody.ui.order.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.liusaprian.foody.model.request.checkout.CheckoutRequest
import app.liusaprian.foody.ui.order.inprogress.InProgressFragment
import app.liusaprian.foody.ui.order.pastorder.PastOrderFragment

class OrderSectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private var inProgressList : ArrayList<CheckoutRequest> = ArrayList()
    private var pastOrderList : ArrayList<CheckoutRequest> = ArrayList()

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                val fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("transaction_list", inProgressList)
                fragment.arguments = bundle
                fragment
            }
            1 -> {
                val fragment = PastOrderFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("transaction_list", pastOrderList)
                fragment.arguments = bundle
                fragment
            }
            else -> {
                val fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("transaction_list", inProgressList)
                fragment.arguments = bundle
                fragment
            }
        }
    }

    fun setData(inProgressList : ArrayList<CheckoutRequest>, pastOrderList : ArrayList<CheckoutRequest>) {
        this.inProgressList = inProgressList
        this.pastOrderList = pastOrderList
    }
}