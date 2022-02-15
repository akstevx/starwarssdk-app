package com.example.starwars_android_sdk

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.starwars_android_sdk.databinding.ActivityMainBinding
import com.example.starwarsdk.StarwarsSdk
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater);
        val view: View = binding.root
        setContentView(view)

        supportActionBar?.show()
        supportActionBar?.title = getString(R.string.app_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        launchSdk()
    }

    private fun launchSdk(){
        binding.btnLaunchSdk.setOnClickListener {
            StarwarsSdk.initialize(activity = this)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}