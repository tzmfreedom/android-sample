package com.freedom_man.standard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class WebviewFragment : Fragment() {
    companion object {
        fun newInstance() = WebviewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val webview = view.findViewById<WebView>(R.id.webview)
        webview.webViewClient = WebViewClient()
        webview.loadUrl("https://google.co.jp")
        return view
    }
}
