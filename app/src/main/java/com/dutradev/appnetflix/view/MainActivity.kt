package com.dutradev.appnetflix.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutradev.appnetflix.R
import com.dutradev.appnetflix.model.Category
import com.dutradev.appnetflix.model.Movie
import com.dutradev.appnetflix.view.adapter.CategoryAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categories = mutableListOf<Category>()
        for (j in 0 until 10) {
            val movies = mutableListOf<Movie>()
            for (i in 0 until 10) {
                val movie = Movie(R.drawable.movie)
                movies.add(movie)
            }
            val category = Category("cat $j", movies)
            categories.add(category)
        }

        val rvMain: RecyclerView = findViewById(R.id.rv_main)
        val adapter = CategoryAdapter(categories)
        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = adapter

    }

}