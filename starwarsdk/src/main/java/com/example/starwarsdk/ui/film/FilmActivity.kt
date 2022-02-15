package com.example.starwarsdk.ui.film

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsdk.R
import com.example.starwarsdk.databinding.ActivityFilmBinding
import com.example.starwarsdk.network.response.GetFilmResponse
import com.example.starwarsdk.ui.adaptor.AdaptorCallback
import com.example.starwarsdk.ui.adaptor.FilmAdaptor
import com.example.starwarsdk.utils.extensions.hide
import com.example.starwarsdk.utils.extensions.show
import com.example.starwarsdk.utils.observeChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmActivity : AppCompatActivity() {
    private var _binding: ActivityFilmBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FilmViewModel by viewModels()
    private val adaptor: FilmAdaptor by lazy { FilmAdaptor(adaptorCallback, mutableListOf()) }
    private val adaptorCallback: AdaptorCallback<GetFilmResponse> = object :
        AdaptorCallback<GetFilmResponse> {
        override fun onClicked(item: GetFilmResponse) {
            Toast.makeText(this@FilmActivity, "You selected ${item.title}", Toast.LENGTH_SHORT)
                .show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilmBinding.inflate(layoutInflater);
        val view: View = binding.root
        setContentView(view)

        supportActionBar?.show()
        supportActionBar?.title = getString(R.string.starwars_movies)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        updateUI()
    }

    private fun updateUI() {
        setObservers()
        setupProgressBar()
        setUpRecycler()

        binding.btnReturn.setOnClickListener { onBackPressed() }
    }

    private fun setObservers() {
        viewModel.getFilmList()

        viewModel.showLoading.observeChange(this) {
            showLoading(it)
        }

        viewModel.filmListObserver.observeChange(this) {
            adaptor.onNewData(it)
        }
    }

    private fun setUpRecycler() {
        binding.recyclerFilms.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerFilms.adapter = adaptor
    }

    private fun showLoading(toShow: Boolean) {
        if (toShow) {
            binding.btnReturn.hide()
            binding.progressBar.show()
        } else {
            binding.btnReturn.show()
            binding.progressBar.hide()
        }
    }

    private fun setupProgressBar() {
        val colorCodeDark = Color.parseColor("#ffff8800")
        binding.progressBar.indeterminateDrawable.setColorFilter(
            colorCodeDark,
            android.graphics.PorterDuff.Mode.MULTIPLY
        )
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