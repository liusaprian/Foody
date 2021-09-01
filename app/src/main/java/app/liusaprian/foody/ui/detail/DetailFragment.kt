package app.liusaprian.foody.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentDetailBinding
import app.liusaprian.foody.model.response.home.FoodItem
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private val binding: FragmentDetailBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).foodData?.let { food ->
                bundle.putParcelable("food", food)
                initView(food)
            }
        }

        binding.detailOrderBtn.setOnClickListener {
            findNavController().navigate(R.id.to_payment, bundle)
        }

        binding.detailBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as DetailActivity).toolbar("", "", false)
    }

    private fun initView(food: FoodItem) {
        with(binding) {
            Glide.with(requireActivity())
                .load(food.picturePath)
                .into(detailFoodImage)
            detailFoodName.text = food.name
            detailRatingBar.rating = food.rate?.toFloat() ?: 0f
            detailFoodRating.text = food.rate?.toFloat().toString()
            detailFoodDescription.text = food.description
            detailFoodIngredients.text = food.ingredients
            detailFoodTotal.text = getString(R.string.food_price, "%,d".format(food.price).replace(',', '.'))
        }
    }

}