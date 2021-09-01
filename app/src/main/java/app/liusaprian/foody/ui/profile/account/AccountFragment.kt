package app.liusaprian.foody.ui.profile.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import app.liusaprian.foody.R
import app.liusaprian.foody.databinding.FragmentAccountBinding
import app.liusaprian.foody.ui.profile.adapter.ProfileMenuAdapter

class AccountFragment : Fragment(), ProfileMenuAdapter.MenuCallback {

    private val binding: FragmentAccountBinding by viewBinding()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProfileMenuAdapter(initDataDummy(), this)
        val layoutManager = LinearLayoutManager(context)

        binding.accountRv.adapter = adapter
        binding.accountRv.layoutManager = layoutManager
    }

    private fun initDataDummy(): ArrayList<String> {
        return arrayListOf(
            "Edit Profile",
            "Home Address",
            "Security",
            "Payment"
        )
    }

    override fun onClick(v: View) {

    }
}