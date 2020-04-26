package com.freedom_man.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.edit.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoEditActivity: AppCompatActivity() {
    private val TAG = TodoEditActivity::class.java.simpleName
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit)

        this.id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")
        val titleView = findViewById<EditText>(R.id.edit_title)
        val bodyView = findViewById<EditText>(R.id.body)
        titleView.setText(title, TextView.BufferType.NORMAL)
        bodyView.setText(body, TextView.BufferType.NORMAL)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_edit, menu)
        val item = menu.getItem(0)
        item.setOnMenuItemClickListener {
            val presenter = TodoPresenter()
            presenter.createTask(
                this.id,
                edit_title.text.toString(),
                body.text.toString(),
                {
                    this.editSuccess()
                }, {
                    this.setFailure(it)
                })
            true
        }
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

    private fun setFailure(error: Throwable) {
        Toast.makeText(this, "failure: " + error.message, Toast.LENGTH_SHORT).show()
    }

    private fun editSuccess() {
        Log.d(TAG, "hoge")
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        val intent = Intent()
        intent.putExtra("id", this.id)
        intent.putExtra("title", edit_title.text.toString())
        intent.putExtra("body", body.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}
