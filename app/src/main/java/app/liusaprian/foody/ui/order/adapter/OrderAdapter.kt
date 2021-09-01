package app.liusaprian.foody.ui.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.OrderItemBinding
import app.liusaprian.foody.model.request.checkout.CheckoutRequest
import app.liusaprian.foody.utils.Helpers.convertToDateTime
import com.bumptech.glide.Glide

class OrderAdapter (
    private val transactions: List<CheckoutRequest>,
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount() = transactions.size

    class ViewHolder(private val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: CheckoutRequest) {
            itemView.apply {
                with(binding) {
                    Glide.with(context)
                        .load(transaction.food.picturePath)
                        .into(orderImage)
                    orderName.text = transaction.food.name
                    orderQuantityPrice.text =
                        resources.getString(
                            R.string.order_qty_price,
                            transaction.quantity,
                            "%,d".format(transaction.total).replace(',', '.')
                        )
                    if(transaction.status.equals("pending", true) ||
                        transaction.status.equals("on_delivery", true)) {
                        orderDate.visibility = View.GONE
                        orderStatus.visibility = View.GONE
                    } else {
                        orderDate.text = transaction.food.createdAt?.convertToDateTime("MMM dd, HH:mm")
                        if(transaction.status.equals("cancelled", true))
                            orderStatus.setTextColor(ContextCompat.getColor(context, R.color.red))
                        else orderStatus.setTextColor(ContextCompat.getColor(context, R.color.green))
                        orderStatus.text = transaction.status
                    }
                }
            }
        }
    }
}