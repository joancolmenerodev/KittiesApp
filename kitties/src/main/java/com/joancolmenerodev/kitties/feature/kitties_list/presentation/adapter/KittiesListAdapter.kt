package com.joancolmenerodev.kitties.feature.kitties_list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joancolmenerodev.kitties.R
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie

class KittiesListAdapter(private val onItemClick: (kittie: Kittie) -> Unit) :
    RecyclerView.Adapter<KittiesListAdapter.KittiesListViewHolder>() {

    private var kittiesList = ArrayList<Kittie>()

    fun addItems(list: List<Kittie>) {
        kittiesList.clear()
        kittiesList.addAll(list)
        notifyDataSetChanged()
    }

    class KittiesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val kittieName: TextView = itemView.findViewById(R.id.tv_kitty_item_name)
        private val kittieDescription: TextView =
            itemView.findViewById(R.id.tv_kitty_item_description)
        private val kittieImage: ImageView = itemView.findViewById(R.id.iv_kitty_item_photo)
        private val cardView: CardView = itemView.findViewById(R.id.card_view_kitty_item)

        fun bind(
            kittie: Kittie,
            onItemClick: (kitty: Kittie) -> Unit
        ) {
            kittieName.text = kittie.name
            kittieDescription.text = kittie.description
            Glide.with(itemView.context).load(kittie.image).into(kittieImage)
            cardView.setOnClickListener { onItemClick.invoke(kittie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KittiesListViewHolder {
        return KittiesListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.kitty_item, parent, false)
        )
    }

    override fun getItemCount() = kittiesList.size

    override fun onBindViewHolder(holder: KittiesListViewHolder, position: Int) {
        holder.bind(kittiesList[position], onItemClick)
    }
}