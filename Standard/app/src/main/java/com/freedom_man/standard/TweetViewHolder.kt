package com.freedom_man.standard

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.freedom_man.standard.databinding.TweetRowBinding
import com.squareup.picasso.Picasso

class TweetViewHolder(val binding: TweetRowBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setIcon(url: String) {
        val imageView = binding.root.findViewById<ImageView>(R.id.icon)
        Picasso.get()
            .load(url)
            .into(imageView);
    }
}
