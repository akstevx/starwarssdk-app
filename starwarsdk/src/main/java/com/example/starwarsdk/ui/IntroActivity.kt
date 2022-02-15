package com.example.starwarsdk.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsdk.SdkCoordinator
import com.example.starwarsdk.databinding.ActivityIntroBinding
import com.example.starwarsdk.ui.adaptor.AdaptorCallback
import com.example.starwarsdk.ui.adaptor.DashboardAdaptor

class IntroActivity : AppCompatActivity() {
    private var _binding: ActivityIntroBinding? = null
    private val binding get() = _binding!!

    private val adaptor: DashboardAdaptor by lazy { DashboardAdaptor(adaptorCallback, this) }
    private val adaptorCallback: AdaptorCallback<DashboardAdaptor.SdkEvents> = object :
        AdaptorCallback<DashboardAdaptor.SdkEvents> {
        override fun onClicked(item: DashboardAdaptor.SdkEvents) {
            when (item.eventPosition) {
                0 -> launchCharacterEvent()
                1 -> launchPlanetEvent()
                2 -> launchFilmEvent()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityIntroBinding.inflate(layoutInflater);
        val view: View = binding.root
        setContentView(view)
        updateUI()
    }

    private fun updateUI() {
        supportActionBar?.hide()
        setUpRecycler()
        closeSdk()
    }

    private fun setUpRecycler() {
        binding.recyclerEvents.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerEvents.adapter = adaptor
    }

    private fun launchCharacterEvent() {
        SdkCoordinator.startCharacterActivity(activity = this)
    }

    private fun launchPlanetEvent() {
        SdkCoordinator.startPlanetActivity(activity = this)
    }

    private fun launchFilmEvent() {
        SdkCoordinator.startFilmActivity(activity = this)
    }


    private fun closeSdk() {
        binding.btnCloseSdk.setOnClickListener { this.finish() }
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}