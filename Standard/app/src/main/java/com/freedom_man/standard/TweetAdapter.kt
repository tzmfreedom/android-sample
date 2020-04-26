package com.freedom_man.standard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class TweetAdapter(private var items: List<TweetItem>) : RecyclerView.Adapter<TweetViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val item = items[position]
//        holder.setTitle(item.title)
        holder.setBody(item.body)
        holder.setIcon("https://i.picsum.photos/id/${position}/50/50.jpg")
        holder.itemView.setOnClickListener {
//            val item = items[holder.adapterPosition]
//            listener.onItemClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.tweet_row, parent, false)
        return TweetViewHolder(inflate)
    }

    fun setItems(items: List<TweetItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}
