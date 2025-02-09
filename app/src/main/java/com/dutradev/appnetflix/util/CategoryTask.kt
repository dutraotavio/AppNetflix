package com.dutradev.appnetflix.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.dutradev.appnetflix.model.Category
import com.dutradev.appnetflix.model.Movie
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask(private val callback: Callback) {

    private val handler = Handler(Looper.getMainLooper())

    interface Callback {
        fun onPreExecute()
        fun onResult(categories: List<Category>)
        fun onFailure(message: String)
    }

    fun execute(url: String) {
        callback.onPreExecute()
        // Nesse momento estamos utilizando a UI-Thread (1)
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            var urlConnection: HttpsURLConnection? = null
            var stream: InputStream? = null

            try {
                //Nesse momento estamos  utilizando a Nova-Thread (processo paralelo) (2)
                val request = URL(url) // Abrir uma URL
                urlConnection = request.openConnection() as HttpsURLConnection // Abrir conexão
                urlConnection.readTimeout = 2000 // Tempo de leitura
                urlConnection.connectTimeout = 2000 //Tempo de conexão

                val statusCode = urlConnection.responseCode
                if (statusCode > 400) {
                    throw IOException("Erro na cominicação com o servidor!")
                }

                stream = urlConnection.inputStream // Sequência de bytes
                val jsonAsString =
                    stream.bufferedReader().use { it.readText() } // Transforma de Bytes para String
                val categories = toCategories(jsonAsString)

                handler.post {
                    // Aqui roda dentro da UI-Thread
                    callback.onResult(categories)
                }

            } catch (e: IOException) {

                val message = e.message ?: "Erro desconhecido"
                Log.e("Teste", message, e)
                handler.post {
                    callback.onFailure(message)
                }

            } finally {
                urlConnection?.disconnect()
                stream?.close()
            }
        }
    }

    private fun toCategories(jsonAsString: String): List<Category> {
        val categories = mutableListOf<Category>()

        val jsonRoot = JSONObject(jsonAsString)
        val jsonCategories = jsonRoot.getJSONArray("category")
        for (i in 0 until jsonCategories.length()) {
            val jsonCategory = jsonCategories.getJSONObject(i)

            val title = jsonCategory.getString("title")
            val jsonMovies = jsonCategory.getJSONArray("movie")

            val movies = mutableListOf<Movie>()
            for (j in 0 until jsonMovies.length()) {
                val jsonMovie = jsonMovies.getJSONObject(j)
                val id = jsonMovie.getInt("id")
                val coverUrl = jsonMovie.getString("cover_url")

                movies.add(Movie(id, coverUrl))
            }

            categories.add(Category(title, movies))
        }

        return categories
    }
}