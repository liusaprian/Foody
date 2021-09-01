package app.liusaprian.foody.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FoodTileItemBinding
import app.liusaprian.foody.model.response.home.FoodItem
import com.bumptech.glide.Glide

class FoodTileAdapter (
    private val foods: List<FoodItem>,
    private val foodCallback: FoodCallback
) : RecyclerView.Adapter<FoodTileAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodTileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(foods[position], foodCallback)
    }

    override fun getItemCount() = foods.size

    interface FoodCallback {
        fun onClick(v: View, data: FoodItem)
    }

    class ViewHolder(private val binding: FoodTileItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food: FoodItem, foodCallback: FoodCallback) {
            itemView.apply {
                with(binding) {
                    Glide.with(context)
                        .load(food.picturePath)
                        .into(tileFoodImage)
                    tileFoodName.text = food.name
                    tileRatingBar.rating = food.rate?.toFloat() ?: 0f
                    tileRatingNum.text = food.rate?.toFloat().toString()
                    tileFoodPrice.text = resources.getString(R.string.food_price, "%,d".format(food.price).replace(',', '.'))
                }

                setOnClickListener {
                    foodCallback.onClick(it, food)
                }
            }
        }
    }
}