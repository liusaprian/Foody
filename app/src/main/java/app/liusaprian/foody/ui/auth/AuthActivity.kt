package app.liusaprian.foody.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private val binding: ActivityAuthBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun toolbar(titleText: String, subtitleText: String, visible: Boolean, visibleLeadingIcon: Boolean = true) {
        binding.header.toolbar.visibility = if(visible) View.VISIBLE else View.GONE
        binding.header.toolbar.apply {
            title = titleText
            subtitle = subtitleText
            if(!visibleLeadingIcon) navigationIcon = null
            else {
                navigationIcon = AppCompatResources.getDrawable(this@AuthActivity, R.drawable.ic_arrow_back)
                setNavigationOnClickListener { onBackPressed() }
            }
        }
    }
}