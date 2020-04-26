package com.freedom_man.todo

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detail.*

class TodoDetailActivity: AppCompatActivity() {
    private val TAG = TodoDetailActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        initView(intent)
    }

    private fun initView(intent: Intent) {
        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")
        val titleView = findViewById<TextView>(R.id.title)
        val bodyView = findViewById<TextView>(R.id.body)
        titleView.text = title
        bodyView.text = body

        fab.setOnClickListener {
            val intent = Intent(this, TodoEditActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("title", title)
            intent.putExtra("body", body)
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let { initView(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
