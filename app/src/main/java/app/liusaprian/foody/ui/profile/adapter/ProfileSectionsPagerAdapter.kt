package app.liusaprian.foody.ui.profile.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import app.liusaprian.foody.ui.profile.account.AccountFragment
import app.liusaprian.foody.ui.profile.foody.FoodyFragment

class ProfileSectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> AccountFragment()
            1 -> FoodyFragment()
            else -> AccountFragment()
        }
    }
}