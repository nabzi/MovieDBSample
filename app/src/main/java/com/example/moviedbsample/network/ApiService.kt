package com.example.moviedbsample.network

import com.example.moviedbsample.model.Movies
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "b8fb74a7f7ebe3f2402f6de80059d5a5"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Movies

    @GET("search/movie")
    suspend fun getSearchedMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") searched: String
    ): Movies

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("with_genres") genres: String
    ): Movies
}

object MoviesApi {
    val retrofitService: MoviesApiService by lazy { retrofit.create(MoviesApiService::class.java) }
}