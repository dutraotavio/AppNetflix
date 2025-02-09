package com.dutradev.appnetflix.model

data class MovieDetail(
    val movie: Movie,
    val similars: List<Movie>
)
