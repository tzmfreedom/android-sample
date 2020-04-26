package com.freedom_man.todo

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TodoAdapter.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val rv = findViewById<RecyclerView>(R.id.todo_items)
        val adapter = TodoAdapter(listOf(), this)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val presenter = TodoPresenter()
        presenter.loadTasks({
            this.setTodoItems(it)
        },{
            this.setFailure(it)
        })
    }

    override fun onResume() {
        super.onResume()
        val presenter = TodoPresenter()
        presenter.loadTasks({
            this.setTodoItems(it)
        },{
            this.setFailure(it)
        })
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

    override fun onItemClick(item: Todo) {
        val intent = Intent(this, TodoDetailActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("title", item.title)
        intent.putExtra("body", item.body)
        startActivity(intent)
    }

    private fun setTodoItems(items: List<Todo>) {
        val rv = findViewById<RecyclerView>(R.id.todo_items)
        (rv.adapter as TodoAdapter).setTodoItems(items)
    }

    private fun setFailure(error: Throwable) {
        Toast.makeText(this, "failure: " + error.message, Toast.LENGTH_SHORT).show()
    }
}
