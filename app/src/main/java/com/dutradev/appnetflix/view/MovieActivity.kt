package com.dutradev.appnetflix.view

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutradev.appnetflix.R
import com.dutradev.appnetflix.model.Movie
import com.dutradev.appnetflix.view.adapter.MovieAdapter

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val txtTitle: TextView = findViewById(R.id.movie_txt_title)
        val txtDesc: TextView = findViewById(R.id.movie_txt_desc)
        val txtCast: TextView = findViewById(R.id.movie_txt_cast)
        val rv: RecyclerView = findViewById(R.id.movie_rv_similar)

        txtTitle.text = "Batman Begins"
        txtDesc.text = "Aqui vai a descrição do filme..."
        txtCast.text = getString(R.string.cast, "Ator A, Ator B, Atriz A, Atriz B")

        val movies = mutableListOf<Movie>()
        for (i in 0..15) {
            val movie = Movie(R.drawable.movie)
            movies.add(movie)
        }

        rv.layoutManager = GridLayoutManager(this, 3)
        rv.adapter = MovieAdapter(movies, R.layout.movie_item_similar)

        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        // Busca o desenhavel (layer-list)
        val layerDrawable: LayerDrawable = ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable

        // Busca o filme
        val movieCover = ContextCompat.getDrawable(this, R.drawable.movie_4)

        // Atribui ao layer-list o novo filme
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)

        // Seta no ImageView
        val coverImg: ImageView = findViewById(R.id.movie_img)
        coverImg.setImageDrawable(layerDrawable)
    }
}