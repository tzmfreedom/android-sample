package com.freedom_man.standard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freedom_man.standard.databinding.CarouselCardRowBinding
import com.freedom_man.standard.databinding.CarouselRowBinding
import com.squareup.picasso.Picasso


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
        val items = listOf(
            CarouselItem("carousel 1", "C1"),
            CarouselItem("carousel 2", "C2"),
            CarouselItem("carousel 3", "C3"),
            CarouselItem("carousel 4", "C4"),
            CarouselItem("carousel 5", "C5")
        )
        val adapter = CarouselAdapter(items)
        view?.findViewById<RecyclerView>(R.id.carousel)?.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        val cardAdapter = CarouselCardAdapter(items)
        view?.findViewById<RecyclerView>(R.id.carousel_card)?.apply {
            this.adapter = cardAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        view?.findViewById<GridLayout>(R.id.image_grid)?.apply {
            for (i in 0..10) {
                for (j in 0..2) {
                    val params = GridLayout.LayoutParams()
                    params.columnSpec = GridLayout.spec(j, 1, 1.0f)
                    params.rowSpec = GridLayout.spec(i)
                    val image = ImageView(context)
                    image.layoutParams = params
                    image.setPadding(10)
                    val url = "https://i.picsum.photos/id/${i*10+j}/200/200.jpg"
                    Picasso.get()
                        .load(url)
                        .into(image)
                    image.setOnClickListener {
                        val ft = fragmentManager?.beginTransaction()
                        ft?.addToBackStack(null)
                        val dialog = ImageDialogFragment.newInstance(url)
                        dialog.show(ft, "dialog")
                    }
                    this.addView(image)
                }
            }
        }
    }

    class ImageDialogFragment : DialogFragment() {
        companion object {
            fun newInstance(url: String): ImageDialogFragment {
                val fragment = ImageDialogFragment()
                val bundle = Bundle()
                bundle.putString("url", url)
                fragment.arguments = bundle
                return fragment
            }
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setStyle(STYLE_NORMAL, R.style.TransparentDialogTheme)
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_dialog, container, false)
            val image = view.findViewById<ImageView>(R.id.dialog_image)
            arguments?.getString("url")?.let {
                Log.d("dialog", it)
                Picasso.get()
                    .load(it)
                    .into(image)
            }
            val button = view.findViewById<ImageButton>(R.id.closeButton)
            button.setOnClickListener {
                dismiss()
            }
            return view
        }
        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            val dialog = dialog
            val lp = dialog.window!!.attributes
            val metrics = resources.displayMetrics

            // 画面サイズの0.8倍の大きさに指定
            val dialogWidth = (metrics.widthPixels * 0.8).toInt()
            val dialogHeight = (metrics.heightPixels * 0.8).toInt()
            lp.width = dialogWidth
            lp.height = dialogHeight
            lp.dimAmount = 0.7f
            dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialog.window!!.attributes = lp
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

    class CarouselCardViewHolder(val binding: CarouselCardRowBinding) : RecyclerView.ViewHolder(binding.root)

    class CarouselCardAdapter(private val items: List<CarouselItem>) : RecyclerView.Adapter<CarouselCardViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselCardViewHolder {
            val binding = DataBindingUtil.inflate<CarouselCardRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.carousel_card_row,
                parent,
                false
            )
            return CarouselCardViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: CarouselCardViewHolder, position: Int) {
            holder.binding.item = items[position]
        }
    }

    class CarouselItem(val title: String, val body: String)
}

