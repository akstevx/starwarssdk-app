package com.example.starwarsdk.ui.planet

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsdk.R
import com.example.starwarsdk.databinding.ActivityPlanetBinding
import com.example.starwarsdk.network.response.GetPlanetResponse
import com.example.starwarsdk.ui.adaptor.AdaptorCallback
import com.example.starwarsdk.ui.adaptor.PlanetAdaptor
import com.example.starwarsdk.utils.extensions.hide
import com.example.starwarsdk.utils.extensions.show
import com.example.starwarsdk.utils.observeChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetActivity : AppCompatActivity() {
    private var _binding: ActivityPlanetBinding?= null
    private val binding get() = _binding!!
    private val viewModel: PlanetViewModel by viewModels()
    private val adaptor: PlanetAdaptor by lazy { PlanetAdaptor(adaptorCallback, mutableListOf()) }
    private val adaptorCallback: AdaptorCallback<GetPlanetResponse> = object :
        AdaptorCallback<GetPlanetResponse> {
        override fun onClicked(item: GetPlanetResponse) {
            Toast.makeText(this@PlanetActivity, "You selected ${item.name}!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlanetBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        supportActionBar?.show()
        supportActionBar?.title = getString(R.string.starwars_planets)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        updateUI()
    }


    private fun updateUI(){
        setupProgressBar()
        setObservers()
        setUpRecycler()
        binding.btnReturn.setOnClickListener { onBackPressed() }
    }

    private fun setObservers() {
        viewModel.getPlanets()

        viewModel.showLoading.observeChange(this){
            showLoading(it)
        }

        viewModel.planetsObserver.observeChange(this){
            adaptor.onNewData(it)
        }
    }

    private fun setUpRecycler(){
        binding.recyclerPlanets.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerPlanets.adapter = adaptor
    }

    private fun showLoading(toShow:Boolean){
        if(toShow) {
            binding.btnReturn.hide()
            binding.progressBar.show()
        } else {
            binding.btnReturn.show()
            binding.progressBar.hide()
        }
    }

    private fun setupProgressBar(){
        val colorCodeDark = Color.parseColor("#ffff8800")
        binding.progressBar.indeterminateDrawable.setColorFilter(colorCodeDark, android.graphics.PorterDuff.Mode.MULTIPLY)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}