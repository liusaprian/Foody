package app.liusaprian.foody.ui.home.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.liusaprian.foody.model.response.home.FoodItem
import app.liusaprian.foody.ui.home.newtaste.NewTasteFragment
import app.liusaprian.foody.ui.home.popular.PopularFragment
import app.liusaprian.foody.ui.home.recommended.RecommendedFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private var newTasteList: ArrayList<FoodItem> = ArrayList()

    fun setNewTasteListData(newTasteList: ArrayList<FoodItem>) {
        this.newTasteList = newTasteList
    }

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                val fragment = NewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                fragment
            }
            1 -> PopularFragment()
            2 -> RecommendedFragment()
            else -> NewTasteFragment()
        }
    }
}