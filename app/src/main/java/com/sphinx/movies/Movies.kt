package com.sphinx.movies

data class Movies(val results: List<MovieResult>)

data class MovieResult (
                  val id: Int,
                  val poster_path: String,
                  val title: String,
                  val vote_average: Float)


data class MovieDetails (val id: Int,
                         val backdrop_path: String,
                         val original_language: String,
                         val title: String,
                         val overview: String,
                         val release_date: String,
                         val vote_average: Float,
                         val budget: Int,
                         val genres: List<Genres>,
                         val production_companies: List<ProductionCompanies>,
                         val production_countries: List<ProductionCountries>,
                         val spoken_languages: List<SpokenLanguages>
)

data class Genres (val name: String)

data class ProductionCompanies(val name: String,
                               val logo_path: String,
                               val origin_country: String)

data class ProductionCountries(val name: String)

data class SpokenLanguages(val name: String)