package app.liusaprian.foody.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.Navigation
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detail_host_fragment))
            val bundle = Bundle()
            bundle.putParcelable("food_data", it.get("food_data") as Parcelable?)
            navController.setGraph(navController.graph, bundle)
        }
    }

    fun toolbar(titleText: String, subtitleText: String, visible: Boolean) {
        binding.header.toolbar.visibility = if(visible) View.VISIBLE else View.GONE
        binding.header.toolbar.apply {
            title = titleText
            subtitle = subtitleText
            navigationIcon = AppCompatResources.getDrawable(this@DetailActivity, R.drawable.ic_arrow_back)
            setNavigationOnClickListener { onBackPressed() }
        }
    }
}