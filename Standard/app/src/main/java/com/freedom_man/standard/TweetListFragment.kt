package com.freedom_man.standard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TweetListFragment : Fragment() {
    companion object {
        const val TAG = "TweetListFragment"
    }
    private lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tweet_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(TweetViewModel::class.java)

        val progressBarFrame = view!!.findViewById<FrameLayout>(R.id.progressBarHolder)
        if (viewModel.items.value.isNullOrEmpty() || viewModel.isExpired()) {
            Log.d(TAG, "Load Start")

            progressBarFrame.visibility = View.VISIBLE
            viewModel.load({
                progressBarFrame.visibility = View.GONE
            }, {
                Toast.makeText(this.context, it.message, Toast.LENGTH_SHORT).show()
            })
        }
        val adapter = TweetAdapter(viewModel, listOf())
        viewModel.items.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observed")
            adapter.setItems(it)
        })
        viewModel.detailIndex.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.toString())
            if (it != -1) {
                findNavController()
                    .navigate(R.id.action_tweetListFragment_to_tweetRecordFragment)
            }
        })
        view?.apply {
            this.findViewById<RecyclerView>(R.id.tweet_items)?.apply {
                this.adapter = adapter
                this.layoutManager = LinearLayoutManager(this@TweetListFragment.context, LinearLayoutManager.VERTICAL, false)
                this.addItemDecoration(DividerItemDecoration(this@TweetListFragment.context, DividerItemDecoration.VERTICAL))
            }
        }
    }
}
