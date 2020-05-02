package com.freedom_man.standard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class TweetFragment : Fragment() {
    companion object {
        fun newInstance() = TweetFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tweet_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback {
            val count = childFragmentManager.findFragmentById(R.id.nav_host_home)!!.childFragmentManager.backStackEntryCount
            if (count == 0) {
                activity?.finish()
            } else {
                Navigation.findNavController(view!!.findViewById(R.id.nav_host_home)).navigateUp()
            }
        }
    }
}
