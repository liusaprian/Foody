package app.liusaprian.foody.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.foody.Foody
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentHomeBinding
import app.liusaprian.foody.model.response.auth.User
import app.liusaprian.foody.model.response.home.FoodItem
import app.liusaprian.foody.model.response.home.FoodResponse
import app.liusaprian.foody.ui.detail.DetailActivity
import app.liusaprian.foody.ui.home.adapter.FoodCardAdapter
import app.liusaprian.foody.ui.home.adapter.SectionsPagerAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class HomeFragment : Fragment(), FoodCardAdapter.FoodCallback, HomeContract.View {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var newTasteList: List<FoodItem> = ArrayList()
    private lateinit var presenter: HomePresenter

    companion object {
        val TAB_TITLES = intArrayOf(R.string.new_taste, R.string.popular, R.string.recommended)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HomePresenter(this)
        presenter.getHomeFoodData()

        val userJson = Foody.getApp().getUser()
        val user = Gson().fromJson(userJson, User::class.java)
        if(user.profilePhotoUrl.isNotEmpty()) {
            Glide.with(requireActivity())
                .load(user.profilePhotoUrl)
                .into(binding.mainProfilePictureIv)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFetchFoodDataSuccess(foodResponse: FoodResponse) {
        newTasteList = foodResponse.data.filter {
            it.types!!.contains("new_food", true)
        }

        val cardAdapter = FoodCardAdapter(foodResponse.data, this)
        val cardLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.popularFoodsRv.adapter = cardAdapter
        binding.popularFoodsRv.layoutManager = cardLayoutManager

        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity() as AppCompatActivity)
        sectionsPagerAdapter.setNewTasteListData(newTasteList as ArrayList<FoodItem>)
        with(binding) {
            viewPagerMain.adapter = sectionsPagerAdapter
            TabLayoutMediator(foodTabs, viewPagerMain) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }

    override fun onFetchFoodDataFailed(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View, data: FoodItem) {
        val toDetail = Intent(activity, DetailActivity::class.java).putExtra("food_data", data)
        startActivity(toDetail)
    }
}