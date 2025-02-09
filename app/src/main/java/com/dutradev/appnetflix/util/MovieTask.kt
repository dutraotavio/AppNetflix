package com.dutradev.appnetflix.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.dutradev.appnetflix.model.Category
import com.dutradev.appnetflix.model.Movie
import com.dutradev.appnetflix.model.MovieDetail
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import java.util.stream.Stream
import javax.net.ssl.HttpsURLConnection

class MovieTask(private val callback: Callback) {

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    interface Callback {
        fun onPreExecute()
        fun onResult(movieDetail: MovieDetail)
        fun onFailure(message: String)
    }

    fun execute(url: String) {
        callback.onPreExecute()
        // Nesse momento estamos utilizando a UI-Thread (1)
        executor.execute {
            var urlConnection: HttpsURLConnection? = null
            var stream: InputStream? = null
            var buffer: BufferedInputStream? = null

            var jsonAsString: String

            try {
                //Nesse momento estamos  utilizando a Nova-Thread (processo paralelo) (2)
                val request = URL(url) // Abrir uma URL
                urlConnection = request.openConnection() as HttpsURLConnection // Abrir conexão
                urlConnection.readTimeout = 2000 // Tempo de leitura
                urlConnection.connectTimeout = 2000 //Tempo de conexão

                val statusCode: Int = urlConnection.responseCode

                if (statusCode == 400) {
                    stream = urlConnection.errorStream
                    jsonAsString =
                        stream.bufferedReader().use { it.readText() }

                    val json = JSONObject(jsonAsString)
                    val message = json.getString("message")
                    throw IOException(message)

                } else if (statusCode > 400) {
                    throw IOException("Erro na cominicação com o servidor!")
                }

                stream = urlConnection.inputStream // Sequência de bytes
                jsonAsString =
                    stream.bufferedReader().use { it.readText() } // Transforma de Bytes para String
                val movieDetail = toMovieDetail(jsonAsString)

                handler.post {
                    // Aqui roda dentro da UI-Thread
                    callback.onResult(movieDetail)
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

    fun toMovieDetail(jsonAsString: String) : MovieDetail {
        val json = JSONObject(jsonAsString)

        val id = json.getInt("id")
        val title = json.getString("title")
        val desc = json.getString("desc")
        val cast = json.getString("cast")
        val coverUrl = json.getString("cover_url")
        val jsonMovies = json.getJSONArray("movie")

        val similars = mutableListOf<Movie>()
        for (i in 0 until jsonMovies.length()) {
            val jsonMovie = jsonMovies.getJSONObject(i)

            val similarId = jsonMovie.getInt("id")
            val similarCoverUrl = jsonMovie.getString("cover_url")

            val m = Movie(similarId, similarCoverUrl)
            similars.add(m)
        }
        val movie = Movie(id, coverUrl, title, desc, cast)
        return MovieDetail(movie, similars)
    }


}