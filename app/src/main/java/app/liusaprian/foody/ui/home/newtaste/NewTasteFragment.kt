package app.liusaprian.foody.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentNewTasteBinding
import app.liusaprian.foody.model.response.home.FoodItem
import app.liusaprian.foody.ui.detail.DetailActivity
import app.liusaprian.foody.ui.home.adapter.FoodTileAdapter

class NewTasteFragment : Fragment(), FoodTileAdapter.FoodCallback {

    private val binding: FragmentNewTasteBinding by viewBinding()
    private var foodList: ArrayList<FoodItem> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_taste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodList = arguments?.getParcelableArrayList("data")!!

        val tileAdapter = FoodTileAdapter(foodList, this)
        val tileLayoutManager = LinearLayoutManager(context)

        binding.newTasteRv.adapter = tileAdapter
        binding.newTasteRv.layoutManager = tileLayoutManager
    }

    override fun onClick(v: View, data: FoodItem) {
        val toDetail = Intent(activity, DetailActivity::class.java)
        toDetail.putExtra("food_data", data)
        startActivity(toDetail)
    }
}