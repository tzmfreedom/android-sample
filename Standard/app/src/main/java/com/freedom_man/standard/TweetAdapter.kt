package com.freedom_man.standard

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.freedom_man.standard.databinding.TweetRowBinding


class TweetAdapter(private val viewModel: TweetViewModel, private var items: List<TweetItem>) : RecyclerView.Adapter<TweetViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tweet = item
        holder.setIcon("https://i.picsum.photos/id/${position}/50/50.jpg")
        holder.itemView.setOnClickListener{
            viewModel.setDetailIndex(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val binding = DataBindingUtil.inflate<TweetRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.tweet_row,
            parent,
            false
        )
        return TweetViewHolder(binding)
    }

    fun setItems(items: List<TweetItem>) {
        if (this.items == items) {
            return
        }
        this.items = items
        notifyDataSetChanged()
    }
}
