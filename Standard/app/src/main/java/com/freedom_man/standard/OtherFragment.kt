package com.freedom_man.standard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freedom_man.standard.databinding.CarouselRowBinding

class OtherFragment : Fragment() {
    companion object {
        fun newInstance() = OtherFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = CarouselAdapter(listOf(
            CarouselItem("carousel 1", "C1"),
            CarouselItem("carousel 2", "C2"),
            CarouselItem("carousel 3", "C3"),
            CarouselItem("carousel 4", "C4"),
            CarouselItem("carousel 5", "C5")
        ))
        view?.findViewById<RecyclerView>(R.id.carousel)?.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    class CarouselViewHolder(val binding: CarouselRowBinding) : RecyclerView.ViewHolder(binding.root)

    class CarouselAdapter(private val items: List<CarouselItem>) : RecyclerView.Adapter<CarouselViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
            val binding = DataBindingUtil.inflate<CarouselRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.carousel_row,
                parent,
                false
            )
            return CarouselViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
            holder.binding.item = items[position]
        }
    }

    class CarouselItem(val title: String, val body: String)
}

