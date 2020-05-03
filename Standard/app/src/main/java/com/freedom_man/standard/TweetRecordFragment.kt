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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.freedom_man.standard.databinding.TweetRecordFragmentBinding


class TweetRecordFragment : Fragment() {
    companion object {
        fun newInstance() = TweetRecordFragment()
    }

    private lateinit var viewModel: TweetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<TweetRecordFragmentBinding>(inflater, R.layout.tweet_record_fragment, container, false)
        viewModel = ViewModelProviders.of(activity!!).get(TweetViewModel::class.java)
        binding.item = viewModel.item
        return binding.root
    }
}
