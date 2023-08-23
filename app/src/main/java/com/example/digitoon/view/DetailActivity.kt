package com.example.digitoon.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.digitoon.common.EXTRA_KEY_DATA
import com.example.digitoon.common.MyActivity
import com.example.digitoon.customView.scroll.ObservableScrollViewCallbacks
import com.example.digitoon.customView.scroll.ScrollState
import com.example.digitoon.databinding.ActivityDetailBinding
import com.example.digitoon.model.Rate
import com.example.digitoon.tech.ImageLoadingService
import com.example.digitoon.viewmodel.DetailViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : MyActivity() {

    private lateinit var binding: ActivityDetailBinding
    val rateListAdapter = RateListAdapter()
    val imageLoadingService: ImageLoadingService by inject()

    val detailViewModel: DetailViewModel by viewModel {
        parametersOf(
            intent.extras!!.getString(
                EXTRA_KEY_DATA
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val rvRating = binding.rvRating
        rvRating.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvRating.adapter = rateListAdapter


        detailViewModel.detailLiveData.observe(this){

            imageLoadingService.load(binding.ivPoster, it.poster)

            binding.tvToolbarTitle.text = it.title

            binding.tvTitle.text = it.title
            binding.tvYear.text = it.year
            binding.tvRuntime.text = it.runtime
            binding.tvImdbRating.text = it.imdbRating
            binding.tvGenre.text = it.genre
            binding.tvDirector.text = it.director
            binding.tvWriter.text = it.writer
            binding.tvActors.text = it.actors
            binding.tvType.text = it.type
            binding.tvRated.text = it.rated
            binding.tvReleased.text = it.released
            binding.tvPlot.text = it.plot
            binding.tvLanguage.text = it.language
            binding.tvCountry.text = it.country
            binding.tvAwards.text = it.awards
            binding.tvMetascore.text = it.metascore
            binding.tvImdbVotes.text = it.imdbVotes

            rateListAdapter.ratings = it as ArrayList<Rate>

        }

        detailViewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

        binding.ivPoster.post {

            val ivPoster = binding.ivPoster
            val ivPosterHeight = ivPoster.height
            val toolbar = binding.viewToolbar


            binding.observableScrollView.addScrollViewCallbacks(object :
                ObservableScrollViewCallbacks {
                override fun onScrollChanged(
                    scrollY: Int,
                    firstScroll: Boolean,
                    dragging: Boolean
                ) {
                    toolbar.alpha = scrollY.toFloat() / ivPosterHeight.toFloat()
                    ivPoster.translationY = scrollY.toFloat() / 2
                }

                override fun onDownMotionEvent() {
                }

                override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
                }

            })
        }

    }
}