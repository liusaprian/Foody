package app.liusaprian.foody.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import app.liusaprian.foody.Foody
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentProfileBinding
import app.liusaprian.foody.model.response.auth.User
import app.liusaprian.foody.ui.profile.adapter.ProfileSectionsPagerAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object {
        val TAB_TITLES = intArrayOf(R.string.account, R.string.app_name)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = ProfileSectionsPagerAdapter(requireActivity() as AppCompatActivity)

        with(binding) {
            viewPagerProfile.adapter = sectionsPagerAdapter
            TabLayoutMediator(profileTabs, viewPagerProfile) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        val user = Gson().fromJson(Foody.getApp().getUser(), User::class.java)

        Glide.with(requireActivity())
            .load(user.profilePhotoUrl)
            .into(binding.profilePictureIv)
        binding.profileNameTv.text = user.name
        binding.profileEmailTv.text = user.email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}