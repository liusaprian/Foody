package app.liusaprian.foody.ui.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.liusaprian.foody.databinding.ProfileItemBinding

class ProfileMenuAdapter(
    private val menu: List<String>,
    private val menuCallback: MenuCallback
) : RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menu[position], menuCallback)
    }

    override fun getItemCount() = menu.size

    interface MenuCallback {
        fun onClick(v: View)
    }

    class ViewHolder(private val binding: ProfileItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: String, menuCallback: MenuCallback) {
            itemView.apply {
                binding.optionName.text = menu
                setOnClickListener { menuCallback.onClick(it) }
            }
        }
    }
}