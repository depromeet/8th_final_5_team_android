package com.example.go_with_dog.view.adapter

import com.example.go_with_dog.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.go_with_dog.databinding.ItemCardBinding
import com.example.go_with_dog.model.SearchUiModel

class CardStackAdapter(
        private var cards: List<SearchUiModel> = emptyList()
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_card,
                        parent,
                        false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cards[position])

//        Glide.with(holder.image)
//            .load(card.url)
//            .into(holder.image)
//
//        holder.itemView.setOnClickListener { v ->
//            Toast.makeText(v.context, card.name, Toast.LENGTH_SHORT).show()
//        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun setSpots(spots: List<SearchUiModel>) {
        this.cards = spots
    }

    fun getSpots(): List<SearchUiModel> {
        return cards
    }

    class ViewHolder(private val binding: ItemCardBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchUiModel) {
            binding.item = item
        }
    }

}
