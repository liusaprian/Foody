package app.liusaprian.foody.ui.detail.payment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.liusaprian.foody.databinding.PaymentItemBinding
import app.liusaprian.foody.model.Order
import com.bumptech.glide.Glide

class PaymentOrderAdapter (
    private val orders: List<Order>,
) : RecyclerView.Adapter<PaymentOrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PaymentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount() = orders.size

    class ViewHolder(private val binding: PaymentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            itemView.apply {
                with(binding) {
                    Glide.with(context)
                        .load(order.food.picturePath)
                        .into(paymentFoodImage)
                    paymentFoodName.text = order.food.name
                    paymentFoodPrice.text = order.food.price.toString()
                    paymentFoodQuantity.text = order.quantity.toString()
                }
            }
        }
    }
}