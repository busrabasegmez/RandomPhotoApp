package com.example.randomphotogenerator

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("photos/random")
    fun getRandomPhoto(@Query("client_id") apiKey: String): Call<PhotoData>
}