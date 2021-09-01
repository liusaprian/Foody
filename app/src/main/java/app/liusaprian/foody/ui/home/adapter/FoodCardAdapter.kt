package app.liusaprian.foody.ui.home.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.liusaprian.foody.databinding.FoodCardItemBinding
import app.liusaprian.foody.model.response.home.FoodItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class FoodCardAdapter (
    private val foods: List<FoodItem>,
    private val foodCallback: FoodCallback
) : RecyclerView.Adapter<FoodCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(foods[position], foodCallback)
    }

    override fun getItemCount() = foods.size

    interface FoodCallback {
        fun onClick(v: View, data: FoodItem)
    }

    class ViewHolder(private val binding: FoodCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(food: FoodItem, foodCallback: FoodCallback) {
            itemView.apply {
                with(binding) {
                    Glide.with(context)
                        .load(food.picturePath)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                shimmerContainer.hideShimmer()
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                shimmerContainer.hideShimmer()
                                return false
                            }

                        })
                        .into(foodImage)
                    foodName.text = food.name
                    foodRating.rating = food.rate?.toFloat() ?: 0f
                    foodRatingNum.text = food.rate?.toFloat().toString()
                }

                setOnClickListener {
                    foodCallback.onClick(it, food)
                }
            }
        }
    }
}