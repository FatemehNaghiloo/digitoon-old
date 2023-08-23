package com.example.digitoon.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitoon.common.implementSpringAnimationTrait
import com.example.digitoon.databinding.ItemFilmBinding
import com.example.digitoon.model.Film
import com.example.digitoon.tech.ImageLoadingService

class FilmListAdapter(val imageLoadingService: ImageLoadingService) :
    RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {

    var onFilmClickListener: OnFilmClickListener? = null

    var films = ArrayList<Film>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(films[position])

    override fun getItemCount(): Int = films.size

    inner class ViewHolder(private val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {

            imageLoadingService.load(binding.ivPoster, film.poster)
            binding.tvTitle.text = film.title
            binding.tvYear.text = film.year

            itemView.implementSpringAnimationTrait()

            itemView.setOnClickListener {
                onFilmClickListener?.onFilmClick(film.imdbId)
            }
        }
    }

    interface OnFilmClickListener {
        fun onFilmClick(imdbId: String)
    }
}