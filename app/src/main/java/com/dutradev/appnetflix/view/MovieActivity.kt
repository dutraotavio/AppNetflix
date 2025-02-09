package com.dutradev.appnetflix.view

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutradev.appnetflix.R
import com.dutradev.appnetflix.model.Movie
import com.dutradev.appnetflix.model.MovieDetail
import com.dutradev.appnetflix.util.DownloadImageTask
import com.dutradev.appnetflix.util.MovieTask
import com.dutradev.appnetflix.view.adapter.MovieAdapter

class MovieActivity : AppCompatActivity(), MovieTask.Callback {

    private lateinit var txtTitle: TextView
    private lateinit var txtDesc: TextView
    private lateinit var txtCast: TextView
    private lateinit var progress: ProgressBar
    private lateinit var adapter: MovieAdapter

    private val movies = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        txtTitle = findViewById(R.id.movie_txt_title)
        txtDesc = findViewById(R.id.movie_txt_desc)
        txtCast = findViewById(R.id.movie_txt_cast)
        progress = findViewById(R.id.movie_progress)

        val rv: RecyclerView = findViewById(R.id.movie_rv_similar)
        val id =
            intent?.getIntExtra("id", 0) ?: throw IllegalStateException("ID não foi encontrado!")
        val url = "https://atway.tiagoaguiar.co/fenix/netflixapp/movie/$id?apiKey=f33c1446-0724-4816-ba2b-735b15cdaf85"

        MovieTask(this).execute(url)

        txtTitle.text = "Batman Begins"
        txtDesc.text = "Aqui vai a descrição do filme..."
        txtCast.text = getString(R.string.cast, "Ator A, Ator B, Atriz A, Atriz B")

        adapter = MovieAdapter(movies, R.layout.movie_item_similar)
        rv.layoutManager = GridLayoutManager(this, 3)
        rv.adapter = adapter

        val toolbar: Toolbar = findViewById(R.id.movie_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        // Busca o desenhavel (layer-list)
        val layerDrawable: LayerDrawable =
            ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable

        // Busca o filme
        val movieCover = ContextCompat.getDrawable(this, R.drawable.placeholder_bg)

        // Atribui ao layer-list o novo filme
        layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)

        // Seta no ImageView
        val coverImg: ImageView = findViewById(R.id.movie_img)
        coverImg.setImageDrawable(layerDrawable)
    }

    override fun onPreExecute() {
        progress.visibility = View.VISIBLE
    }

    override fun onFailure(message: String) {
        progress.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResult(movieDetail: MovieDetail) {
        progress.visibility = View.GONE

        txtTitle.text = movieDetail.movie.title
        txtDesc.text = movieDetail.movie.desc
        txtCast.text = getString(R.string.cast, movieDetail.movie.cast)

        movies.clear()
        movies.addAll(movieDetail.similars)
        adapter.notifyDataSetChanged()

        DownloadImageTask(object : DownloadImageTask.Callback {
            override fun onResult(bitmap: Bitmap) {
                val layerDrawable: LayerDrawable = ContextCompat.getDrawable(this@MovieActivity, R.drawable.shadows) as LayerDrawable
                val movieCover = BitmapDrawable(resources, bitmap)
                layerDrawable.setDrawableByLayerId(R.id.cover_drawable, movieCover)
                val coverImg: ImageView = findViewById(R.id.movie_img)
                coverImg.setImageDrawable(layerDrawable)
            }
        }).execute(movieDetail.movie.coverUrl)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}