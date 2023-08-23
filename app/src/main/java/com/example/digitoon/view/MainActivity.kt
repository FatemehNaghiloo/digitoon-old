package com.example.digitoon.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.digitoon.common.EXTRA_KEY_DATA
import com.example.digitoon.common.MyActivity
import com.example.digitoon.databinding.ActivityMainBinding
import com.example.digitoon.model.Film
import com.example.digitoon.viewmodel.FilmViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : MyActivity(), FilmListAdapter.OnFilmClickListener {

    private lateinit var binding: ActivityMainBinding

    val filmViewModel: FilmViewModel by viewModel()
    val filmListAdapter: FilmListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this, 2)
        val rvFilm = binding.rvFilm
        rvFilm.layoutManager = gridLayoutManager
        rvFilm.adapter = filmListAdapter
        filmListAdapter.onFilmClickListener = this


        filmViewModel.filmsLiveData.observe(this){
            filmListAdapter.films = it as ArrayList<Film>
        }

        filmViewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

    }

    override fun onFilmClick(imdbId: String) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, imdbId)
        })
    }
}