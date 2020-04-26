package com.freedom_man.standard

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val iconView: ImageView = itemView.findViewById(R.id.icon)
    private val bodyView: TextView = itemView.findViewById(R.id.body)

    fun setBody(body: String) {
        bodyView.text = body
    }

    fun setIcon(url: String) {
        Picasso.get()
            .load(url)
            .into(iconView);
    }
}
