package com.freedom_man.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private var items: List<Todo>, private val listener: OnClickListener) : RecyclerView.Adapter<TodoViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = items[position]
        holder.setTitle(item.title)
        holder.setBody(item.body)
        holder.itemView.setOnClickListener {
            val item = items[holder.adapterPosition]
            listener.onItemClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return TodoViewHolder(inflate)
    }

    fun setTodoItems(items: List<Todo>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onItemClick(item: Todo)
    }
}
