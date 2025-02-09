package com.dutradev.appnetflix.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutradev.appnetflix.R
import com.dutradev.appnetflix.model.Category
import com.dutradev.appnetflix.model.Movie
import com.dutradev.appnetflix.util.CategoryTask
import com.dutradev.appnetflix.view.adapter.CategoryAdapter

class MainActivity : AppCompatActivity(), CategoryTask.Callback {

    private lateinit var progress: ProgressBar
    private val categories = mutableListOf<Category>()
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = findViewById(R.id.progress_main)

        val rvMain: RecyclerView = findViewById(R.id.rv_main)
        adapter = CategoryAdapter(categories) { id ->
            val intent = Intent(this@MainActivity, MovieActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

        }
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = adapter

        CategoryTask(this).execute("https://atway.tiagoaguiar.co/fenix/netflixapp/home?apiKey=f33c1446-0724-4816-ba2b-735b15cdaf85")

    }

    override fun onPreExecute() {
        progress.visibility = View.VISIBLE
    }

    override fun onResult(categories: List<Category>) {
        // Aqui será quando o CategoryTask chamará de volta (callback ou listener)
        this.categories.clear()
        this.categories.addAll(categories)
        adapter.notifyDataSetChanged() // Força o adapter a chamar de novo o onBindViewHolder, etc
        progress.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        progress.visibility = View.GONE
    }

}