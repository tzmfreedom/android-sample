package com.freedom_man.todo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleView: TextView = itemView.findViewById(R.id.title)
    private val bodyView: TextView = itemView.findViewById(R.id.body)

    fun setTitle(title: String) {
        titleView.text = title
    }

    fun setBody(body: String) {
        bodyView.text = body
    }
}
