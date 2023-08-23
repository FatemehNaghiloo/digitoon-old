package com.example.digitoon.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitoon.databinding.ItemRateBinding
import com.example.digitoon.model.Rate

class RateListAdapter :
    RecyclerView.Adapter<RateListAdapter.ViewHolder>() {

    var ratings = ArrayList<Rate>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRateBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(ratings[position])

    override fun getItemCount(): Int = ratings.size

    inner class ViewHolder(private val binding: ItemRateBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rate: Rate) {

            binding.tvSource.text = rate.source
            binding.tvValue.text = rate.value

        }
    }

}