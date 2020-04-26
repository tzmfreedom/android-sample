package com.freedom_man.standard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TweetFragment : Fragment() {
    var adapter: TweetAdapter? = null

    companion object {
        fun newInstance() = TweetFragment()
    }

    private lateinit var viewModel: TweetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tweet_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TweetViewModel::class.java)
        viewModel.load {
            Toast.makeText(this.context, it.message, Toast.LENGTH_SHORT).show()
        }
        viewModel.items.observe(this, Observer {
            this@TweetFragment.adapter?.setItems(it)
        })
        view?.apply {
            this@TweetFragment.adapter = TweetAdapter(listOf())
            this.findViewById<RecyclerView>(R.id.tweet_items)?.apply {
                this.adapter = this@TweetFragment.adapter
                this.layoutManager = LinearLayoutManager(this@TweetFragment.context, LinearLayoutManager.VERTICAL, false)
                this.addItemDecoration(DividerItemDecoration(this@TweetFragment.context, DividerItemDecoration.VERTICAL))
            }
        }
    }

}
